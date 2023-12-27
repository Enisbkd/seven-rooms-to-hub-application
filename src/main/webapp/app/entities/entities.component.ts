import { defineComponent, provide } from 'vue';

import TableNumberService from './table-number/table-number.service';
import ClientService from './client/client.service';
import MemberGroupService from './member-group/member-group.service';
import ClientTagService from './client-tag/client-tag.service';
import CustomFieldService from './custom-field/custom-field.service';
import ClientVenueStatsService from './client-venue-stats/client-venue-stats.service';
import ClientPhotoService from './client-photo/client-photo.service';
import ReservationService from './reservation/reservation.service';
import ResCustomFieldService from './res-custom-field/res-custom-field.service';
import ResPosTicketService from './res-pos-ticket/res-pos-ticket.service';
import ResPosticketsItemService from './res-postickets-item/res-postickets-item.service';
import ResTagService from './res-tag/res-tag.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('tableNumberService', () => new TableNumberService());
    provide('clientService', () => new ClientService());
    provide('memberGroupService', () => new MemberGroupService());
    provide('clientTagService', () => new ClientTagService());
    provide('customFieldService', () => new CustomFieldService());
    provide('clientVenueStatsService', () => new ClientVenueStatsService());
    provide('clientPhotoService', () => new ClientPhotoService());
    provide('reservationService', () => new ReservationService());
    provide('resCustomFieldService', () => new ResCustomFieldService());
    provide('resPosTicketService', () => new ResPosTicketService());
    provide('resPosticketsItemService', () => new ResPosticketsItemService());
    provide('resTagService', () => new ResTagService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
