import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUserextra } from '@/shared/model/userextra.model';
import UserextraService from './userextra.service';

@Component
export default class UserextraDetails extends Vue {
  @Inject('userextraService') private userextraService: () => UserextraService;
  public userextra: IUserextra = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userextraId) {
        vm.retrieveUserextra(to.params.userextraId);
      }
    });
  }

  public retrieveUserextra(userextraId) {
    this.userextraService()
      .find(userextraId)
      .then(res => {
        this.userextra = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
