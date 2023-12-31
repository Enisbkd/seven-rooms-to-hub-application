/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ClientVenueStats from './client-venue-stats.vue';
import ClientVenueStatsService from './client-venue-stats.service';
import AlertService from '@/shared/alert/alert.service';

type ClientVenueStatsComponentType = InstanceType<typeof ClientVenueStats>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ClientVenueStats Management Component', () => {
    let clientVenueStatsServiceStub: SinonStubbedInstance<ClientVenueStatsService>;
    let mountOptions: MountingOptions<ClientVenueStatsComponentType>['global'];

    beforeEach(() => {
      clientVenueStatsServiceStub = sinon.createStubInstance<ClientVenueStatsService>(ClientVenueStatsService);
      clientVenueStatsServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          jhiItemCount: true,
          bPagination: true,
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'jhi-sort-indicator': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          clientVenueStatsService: () => clientVenueStatsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        clientVenueStatsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ClientVenueStats, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(clientVenueStatsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.clientVenueStats[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(ClientVenueStats, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(clientVenueStatsServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: ClientVenueStatsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ClientVenueStats, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        clientVenueStatsServiceStub.retrieve.reset();
        clientVenueStatsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        clientVenueStatsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(clientVenueStatsServiceStub.retrieve.called).toBeTruthy();
        expect(comp.clientVenueStats[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(clientVenueStatsServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        clientVenueStatsServiceStub.retrieve.reset();
        clientVenueStatsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(clientVenueStatsServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.clientVenueStats[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(clientVenueStatsServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        clientVenueStatsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeClientVenueStats();
        await comp.$nextTick(); // clear components

        // THEN
        expect(clientVenueStatsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(clientVenueStatsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
