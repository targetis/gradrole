/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UserextraDetailComponent from '@/entities/userextra/userextra-details.vue';
import UserextraClass from '@/entities/userextra/userextra-details.component';
import UserextraService from '@/entities/userextra/userextra.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Userextra Management Detail Component', () => {
    let wrapper: Wrapper<UserextraClass>;
    let comp: UserextraClass;
    let userextraServiceStub: SinonStubbedInstance<UserextraService>;

    beforeEach(() => {
      userextraServiceStub = sinon.createStubInstance<UserextraService>(UserextraService);

      wrapper = shallowMount<UserextraClass>(UserextraDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { userextraService: () => userextraServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUserextra = { id: 123 };
        userextraServiceStub.find.resolves(foundUserextra);

        // WHEN
        comp.retrieveUserextra(123);
        await comp.$nextTick();

        // THEN
        expect(comp.userextra).toBe(foundUserextra);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUserextra = { id: 123 };
        userextraServiceStub.find.resolves(foundUserextra);

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
