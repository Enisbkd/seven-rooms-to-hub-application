/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResTableUpdate from './res-table-update.vue';
import ResTableService from './res-table.service';
import AlertService from '@/shared/alert/alert.service';

import ReservationService from '@/entities/reservation/reservation.service';

type ResTableUpdateComponentType = InstanceType<typeof ResTableUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resTableSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ResTableUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ResTable Management Update Component', () => {
    let comp: ResTableUpdateComponentType;
    let resTableServiceStub: SinonStubbedInstance<ResTableService>;

    beforeEach(() => {
      route = {};
      resTableServiceStub = sinon.createStubInstance<ResTableService>(ResTableService);
      resTableServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          resTableService: () => resTableServiceStub,
          reservationService: () =>
            sinon.createStubInstance<ReservationService>(ReservationService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ResTableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resTable = resTableSample;
        resTableServiceStub.update.resolves(resTableSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resTableServiceStub.update.calledWith(resTableSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        resTableServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ResTableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resTable = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resTableServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        resTableServiceStub.find.resolves(resTableSample);
        resTableServiceStub.retrieve.resolves([resTableSample]);

        // WHEN
        route = {
          params: {
            resTableId: '' + resTableSample.id,
          },
        };
        const wrapper = shallowMount(ResTableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.resTable).toMatchObject(resTableSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resTableServiceStub.find.resolves(resTableSample);
        const wrapper = shallowMount(ResTableUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
