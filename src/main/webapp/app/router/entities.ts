import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Student = () => import('@/entities/student/student.vue');
// prettier-ignore
const StudentUpdate = () => import('@/entities/student/student-update.vue');
// prettier-ignore
const StudentDetails = () => import('@/entities/student/student-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/student',
    name: 'Student',
    component: Student,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/student/new',
    name: 'StudentCreate',
    component: StudentUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/student/:studentId/edit',
    name: 'StudentEdit',
    component: StudentUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/student/:studentId/view',
    name: 'StudentView',
    component: StudentDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
