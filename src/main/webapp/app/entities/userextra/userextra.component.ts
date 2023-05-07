import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUserextra } from '@/shared/model/userextra.model';

import UserextraService from './userextra.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Userextra extends Vue {
  @Inject('userextraService') private userextraService: () => UserextraService;
  private removeId: number = null;

  public userextras: IUserextra[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUserextras();
  }

  public clear(): void {
    this.retrieveAllUserextras();
  }

  public retrieveAllUserextras(): void {
    this.isFetching = true;

    this.userextraService()
      .retrieve()
      .then(
        res => {
          this.userextras = res.data;
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

  public prepareRemove(instance: IUserextra): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUserextra(): void {
    this.userextraService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('gradroleApp.userextra.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUserextras();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
