import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Student = () => import('@/entities/student/student.vue');
// prettier-ignore
const StudentUpdate = () => import('@/entities/student/student-update.vue');
// prettier-ignore
const StudentDetails = () => import('@/entities/student/student-details.vue');
// prettier-ignore
const UserExt = () => import('@/entities/user-ext/user-ext.vue');
// prettier-ignore
const UserExtUpdate = () => import('@/entities/user-ext/user-ext-update.vue');
// prettier-ignore
const UserExtDetails = () => import('@/entities/user-ext/user-ext-details.vue');
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
  {
    path: '/user-ext',
    name: 'UserExt',
    component: UserExt,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/user-ext/new',
    name: 'UserExtCreate',
    component: UserExtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/user-ext/:userExtId/edit',
    name: 'UserExtEdit',
    component: UserExtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/user-ext/:userExtId/view',
    name: 'UserExtView',
    component: UserExtDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
