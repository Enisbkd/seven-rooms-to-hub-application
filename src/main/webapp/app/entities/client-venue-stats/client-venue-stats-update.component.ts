import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ClientVenueStatsService from './client-venue-stats.service';
import { useValidation, useDateFormat } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IClientVenueStats, ClientVenueStats } from '@/shared/model/client-venue-stats.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClientVenueStatsUpdate',
  setup() {
    const clientVenueStatsService = inject('clientVenueStatsService', () => new ClientVenueStatsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clientVenueStats: Ref<IClientVenueStats> = ref(new ClientVenueStats());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveClientVenueStats = async clientVenueStatsId => {
      try {
        const res = await clientVenueStatsService().find(clientVenueStatsId);
        res.techCreatedDate = new Date(res.techCreatedDate);
        res.techUpdatedDate = new Date(res.techUpdatedDate);
        clientVenueStats.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clientVenueStatsId) {
      retrieveClientVenueStats(route.params.clientVenueStatsId);
    }

    const initRelationships = () => {};

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      totalSpendLocalperCover: {},
      lastVisitDate: {},
      totalCancellations: {},
      totalCovers: {},
      avgRating: {},
      totalSpendperCover: {},
      totalSpend: {},
      totalNoShows: {},
      numRatings: {},
      totalSpendPerVisit: {},
      totalSpendLocal: {},
      totalSpendLocalPerVisit: {},
      totalVisits: {},
      grossTotal: {},
      totalOrderCount: {},
      totalOrderCancellations: {},
      totalOrderSpend: {},
      grossOrderTotal: {},
      totalOrderSpendLocal: {},
      lastOrderDate: {},
      totalSpendperOrder: {},
      totalSpendLocalperOrder: {},
      venueId: {},
      venueMarketingOptin: {},
      venueMarketingOptints: {},
      techLineage: {},
      techCreatedDate: {},
      techUpdatedDate: {},
      techMapping: {},
      techComment: {},
      bookingNames: {},
      client: {},
    };
    const v$ = useVuelidate(validationRules, clientVenueStats as any);
    v$.value.$validate();

    return {
      clientVenueStatsService,
      alertService,
      clientVenueStats,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      ...useDateFormat({ entityRef: clientVenueStats }),
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.clientVenueStats.id) {
        this.clientVenueStatsService()
          .update(this.clientVenueStats)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('sevenRoomsToHubApplicationApp.clientVenueStats.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.clientVenueStatsService()
          .create(this.clientVenueStats)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(
              this.t$('sevenRoomsToHubApplicationApp.clientVenueStats.created', { param: param.id }).toString(),
            );
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
