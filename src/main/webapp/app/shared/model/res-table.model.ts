import { type IReservation } from '@/shared/model/reservation.model';

export interface IResTable {
  id?: number;
  tableNumber?: number | null;
  reservation?: IReservation | null;
}

export class ResTable implements IResTable {
  constructor(
    public id?: number,
    public tableNumber?: number | null,
    public reservation?: IReservation | null,
  ) {}
}
