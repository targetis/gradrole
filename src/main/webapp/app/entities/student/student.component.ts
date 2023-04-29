import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IStudent } from '@/shared/model/student.model';

import StudentService from './student.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Student extends Vue {
  @Inject('studentService') private studentService: () => StudentService;
  private removeId: number = null;

  public students: IStudent[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllStudents();
  }

  public clear(): void {
    this.retrieveAllStudents();
  }

  public retrieveAllStudents(): void {
    this.isFetching = true;

    this.studentService()
      .retrieve()
      .then(
        res => {
          this.students = res.data;
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

  public prepareRemove(instance: IStudent): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeStudent(): void {
    this.studentService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('gradroleApp.student.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllStudents();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
