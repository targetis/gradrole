import { Component, Vue, Inject } from 'vue-property-decorator';

import UserService from '@/admin/user-management/user-management.service';

import { IUserextra, Userextra } from '@/shared/model/userextra.model';
import UserextraService from './userextra.service';

const validations: any = {
  userextra: {
    middlename: {},
    jobrole: {},
    dob: {},
  },
};

@Component({
  validations,
})
export default class UserextraUpdate extends Vue {
  @Inject('userextraService') private userextraService: () => UserextraService;
  public userextra: IUserextra = new Userextra();

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userextraId) {
        vm.retrieveUserextra(to.params.userextraId);
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
    if (this.userextra.id) {
      this.userextraService()
        .update(this.userextra)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gradroleApp.userextra.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.userextraService()
        .create(this.userextra)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gradroleApp.userextra.created', { param: param.id });
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

  public retrieveUserextra(userextraId): void {
    this.userextraService()
      .find(userextraId)
      .then(res => {
        this.userextra = res;
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
