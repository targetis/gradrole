import { Component, Vue, Inject } from 'vue-property-decorator';

import { IStudent, Student } from '@/shared/model/student.model';
import StudentService from './student.service';

const validations: any = {
  student: {
    firstname: {},
    lastname: {},
    email: {},
    dob: {},
    course: {},
  },
};

@Component({
  validations,
})
export default class StudentUpdate extends Vue {
  @Inject('studentService') private studentService: () => StudentService;
  public student: IStudent = new Student();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.studentId) {
        vm.retrieveStudent(to.params.studentId);
      }
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
    if (this.student.id) {
      this.studentService()
        .update(this.student)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gradroleApp.student.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.studentService()
        .create(this.student)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('gradroleApp.student.created', { param: param.id });
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

  public retrieveStudent(studentId): void {
    this.studentService()
      .find(studentId)
      .then(res => {
        this.student = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
