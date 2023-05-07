/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import UserextraComponent from '@/entities/userextra/userextra.vue';
import UserextraClass from '@/entities/userextra/userextra.component';
import UserextraService from '@/entities/userextra/userextra.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Userextra Management Component', () => {
    let wrapper: Wrapper<UserextraClass>;
    let comp: UserextraClass;
    let userextraServiceStub: SinonStubbedInstance<UserextraService>;

    beforeEach(() => {
      userextraServiceStub = sinon.createStubInstance<UserextraService>(UserextraService);
      userextraServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<UserextraClass>(UserextraComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          userextraService: () => userextraServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      userextraServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllUserextras();
      await comp.$nextTick();

      // THEN
      expect(userextraServiceStub.retrieve.called).toBeTruthy();
      expect(comp.userextras[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      userextraServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeUserextra();
      await comp.$nextTick();

      // THEN
      expect(userextraServiceStub.delete.called).toBeTruthy();
      expect(userextraServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
