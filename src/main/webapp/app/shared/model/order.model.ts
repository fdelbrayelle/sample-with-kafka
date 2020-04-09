export interface IOrder {
  id?: number;
  amount?: number;
}

export class Order implements IOrder {
  constructor(public id?: number, public amount?: number) {}
}
