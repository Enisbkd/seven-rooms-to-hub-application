import { type IClient } from '@/shared/model/client.model';

export interface IClientTag {
  id?: number;
  tag?: string | null;
  tagDisplay?: string | null;
  group?: string | null;
  groupDisplay?: string | null;
  color?: string | null;
  techLineage?: string | null;
  techCreatedDate?: Date | null;
  techUpdatedDate?: Date | null;
  techMapping?: string | null;
  techComment?: string | null;
  client?: IClient | null;
}

export class ClientTag implements IClientTag {
  constructor(
    public id?: number,
    public tag?: string | null,
    public tagDisplay?: string | null,
    public group?: string | null,
    public groupDisplay?: string | null,
    public color?: string | null,
    public techLineage?: string | null,
    public techCreatedDate?: Date | null,
    public techUpdatedDate?: Date | null,
    public techMapping?: string | null,
    public techComment?: string | null,
    public client?: IClient | null,
  ) {}
}
