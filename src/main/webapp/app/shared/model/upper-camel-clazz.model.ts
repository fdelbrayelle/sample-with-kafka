export interface IUpperCamelClazz {
  id?: number;
}

export class UpperCamelClazz implements IUpperCamelClazz {
  constructor(public id?: number) {}
}
