import { type IBookingName } from '@/shared/model/booking-name.model';
import { type IClient } from '@/shared/model/client.model';

export interface IClientVenueStats {
  id?: number;
  totalSpendLocalperCover?: number | null;
  lastVisitDate?: string | null;
  totalCancellations?: number | null;
  totalCovers?: number | null;
  avgRating?: number | null;
  totalSpendperCover?: number | null;
  totalSpend?: number | null;
  totalNoShows?: number | null;
  numRatings?: number | null;
  totalSpendPerVisit?: number | null;
  totalSpendLocal?: number | null;
  totalSpendLocalPerVisit?: number | null;
  totalVisits?: number | null;
  grossTotal?: number | null;
  totalOrderCount?: number | null;
  totalOrderCancellations?: number | null;
  totalOrderSpend?: number | null;
  grossOrderTotal?: number | null;
  totalOrderSpendLocal?: number | null;
  lastOrderDate?: string | null;
  totalSpendperOrder?: number | null;
  totalSpendLocalperOrder?: number | null;
  venueId?: string | null;
  venueMarketingOptin?: boolean | null;
  venueMarketingOptints?: string | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  bookingNames?: IBookingName[] | null;
  client?: IClient | null;
}

export class ClientVenueStats implements IClientVenueStats {
  constructor(
    public id?: number,
    public totalSpendLocalperCover?: number | null,
    public lastVisitDate?: string | null,
    public totalCancellations?: number | null,
    public totalCovers?: number | null,
    public avgRating?: number | null,
    public totalSpendperCover?: number | null,
    public totalSpend?: number | null,
    public totalNoShows?: number | null,
    public numRatings?: number | null,
    public totalSpendPerVisit?: number | null,
    public totalSpendLocal?: number | null,
    public totalSpendLocalPerVisit?: number | null,
    public totalVisits?: number | null,
    public grossTotal?: number | null,
    public totalOrderCount?: number | null,
    public totalOrderCancellations?: number | null,
    public totalOrderSpend?: number | null,
    public grossOrderTotal?: number | null,
    public totalOrderSpendLocal?: number | null,
    public lastOrderDate?: string | null,
    public totalSpendperOrder?: number | null,
    public totalSpendLocalperOrder?: number | null,
    public venueId?: string | null,
    public venueMarketingOptin?: boolean | null,
    public venueMarketingOptints?: string | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public bookingNames?: IBookingName[] | null,
    public client?: IClient | null,
  ) {
    this.venueMarketingOptin = this.venueMarketingOptin ?? false;
  }
}
