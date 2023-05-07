/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import UserextraUpdateComponent from '@/entities/userextra/userextra-update.vue';
import UserextraClass from '@/entities/userextra/userextra-update.component';
import UserextraService from '@/entities/userextra/userextra.service';

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
  describe('Userextra Management Update Component', () => {
    let wrapper: Wrapper<UserextraClass>;
    let comp: UserextraClass;
    let userextraServiceStub: SinonStubbedInstance<UserextraService>;

    beforeEach(() => {
      userextraServiceStub = sinon.createStubInstance<UserextraService>(UserextraService);

      wrapper = shallowMount<UserextraClass>(UserextraUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          userextraService: () => userextraServiceStub,

          userService: () => new UserService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.userextra = entity;
        userextraServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userextraServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.userextra = entity;
        userextraServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(userextraServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUserextra = { id: 123 };
        userextraServiceStub.find.resolves(foundUserextra);
        userextraServiceStub.retrieve.resolves([foundUserextra]);

        // WHEN
        comp.beforeRouteEnter({ params: { userextraId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.userextra).toBe(foundUserextra);
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
