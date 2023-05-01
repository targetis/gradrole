/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import UserExtUpdateComponent from '@/entities/user-ext/user-ext-update.vue';
import UserExtClass from '@/entities/user-ext/user-ext-update.component';
import UserExtService from '@/entities/user-ext/user-ext.service';

import UserService from '@/admin/user-management/user-management.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('UserExt Management Update Component', () => {
    let wrapper: Wrapper<UserExtClass>;
    let comp: UserExtClass;
    let userExtServiceStub: SinonStubbedInstance<UserExtService>;

    beforeEach(() => {
      userExtServiceStub = sinon.createStubInstance<UserExtService>(UserExtService);

      wrapper = shallowMount<UserExtClass>(UserExtUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          userExtService: () => userExtServiceStub,

          userService: () => new UserService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.userExt = entity;
        userExtServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userExtServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.userExt = entity;
        userExtServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userExtServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUserExt = { id: 123 };
        userExtServiceStub.find.resolves(foundUserExt);
        userExtServiceStub.retrieve.resolves([foundUserExt]);

        // WHEN
        comp.beforeRouteEnter({ params: { userExtId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.userExt).toBe(foundUserExt);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
