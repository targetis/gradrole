import { Component, Vue, Inject } from 'vue-property-decorator';

import UserService from '@/admin/user-management/user-management.service';

import { IUserExt, UserExt } from '@/shared/model/user-ext.model';
import UserExtService from './user-ext.service';

const validations: any = {
  userExt: {
    middleName: {},
    jobRole: {},
    dateOfBirth: {},
  },
};

@Component({
  validations,
})
export default class UserExtUpdate extends Vue {
  @Inject('userExtService') private userExtService: () => UserExtService;
  public userExt: IUserExt = new UserExt();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userExtId) {
        vm.retrieveUserExt(to.params.userExtId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.userExt.id) {
      this.userExtService()
        .update(this.userExt)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gradroleApp.userExt.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.userExtService()
        .create(this.userExt)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gradroleApp.userExt.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveUserExt(userExtId): void {
    this.userExtService()
      .find(userExtId)
      .then(res => {
        this.userExt = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
  }
}
