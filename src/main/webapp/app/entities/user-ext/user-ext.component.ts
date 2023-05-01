import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUserExt } from '@/shared/model/user-ext.model';

import UserExtService from './user-ext.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UserExt extends Vue {
  @Inject('userExtService') private userExtService: () => UserExtService;
  private removeId: number = null;

  public userExts: IUserExt[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUserExts();
  }

  public clear(): void {
    this.retrieveAllUserExts();
  }

  public retrieveAllUserExts(): void {
    this.isFetching = true;

    this.userExtService()
      .retrieve()
      .then(
        res => {
          this.userExts = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IUserExt): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUserExt(): void {
    this.userExtService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('gradroleApp.userExt.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUserExts();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
