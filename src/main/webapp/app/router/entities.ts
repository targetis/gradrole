import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Userextra = () => import('@/entities/userextra/userextra.vue');
// prettier-ignore
const UserextraUpdate = () => import('@/entities/userextra/userextra-update.vue');
// prettier-ignore
const UserextraDetails = () => import('@/entities/userextra/userextra-details.vue');
// prettier-ignore
const Student = () => import('@/entities/student/student.vue');
// prettier-ignore
const StudentUpdate = () => import('@/entities/student/student-update.vue');
// prettier-ignore
const StudentDetails = () => import('@/entities/student/student-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/userextra',
    name: 'Userextra',
    component: Userextra,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/userextra/new',
    name: 'UserextraCreate',
    component: UserextraUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/userextra/:userextraId/edit',
    name: 'UserextraEdit',
    component: UserextraUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/userextra/:userextraId/view',
    name: 'UserextraView',
    component: UserextraDetails,
    meta: { authorities: [Authority.USER] },
  },
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
