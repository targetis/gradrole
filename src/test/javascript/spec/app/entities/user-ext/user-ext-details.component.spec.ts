/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import UserExtDetailComponent from '@/entities/user-ext/user-ext-details.vue';
import UserExtClass from '@/entities/user-ext/user-ext-details.component';
import UserExtService from '@/entities/user-ext/user-ext.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('UserExt Management Detail Component', () => {
    let wrapper: Wrapper<UserExtClass>;
    let comp: UserExtClass;
    let userExtServiceStub: SinonStubbedInstance<UserExtService>;

    beforeEach(() => {
      userExtServiceStub = sinon.createStubInstance<UserExtService>(UserExtService);

      wrapper = shallowMount<UserExtClass>(UserExtDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { userExtService: () => userExtServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundUserExt = { id: 123 };
        userExtServiceStub.find.resolves(foundUserExt);

        // WHEN
        comp.retrieveUserExt(123);
        await comp.$nextTick();

        // THEN
        expect(comp.userExt).toBe(foundUserExt);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundUserExt = { id: 123 };
        userExtServiceStub.find.resolves(foundUserExt);

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
