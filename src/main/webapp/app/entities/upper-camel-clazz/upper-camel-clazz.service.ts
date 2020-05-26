import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUpperCamelClazz } from 'app/shared/model/upper-camel-clazz.model';

type EntityResponseType = HttpResponse<IUpperCamelClazz>;
type EntityArrayResponseType = HttpResponse<IUpperCamelClazz[]>;

@Injectable({ providedIn: 'root' })
export class UpperCamelClazzService {
  public resourceUrl = SERVER_API_URL + 'api/upper-camel-clazzes';

  constructor(protected http: HttpClient) {}

  create(upperCamelClazz: IUpperCamelClazz): Observable<EntityResponseType> {
    return this.http.post<IUpperCamelClazz>(this.resourceUrl, upperCamelClazz, { observe: 'response' });
  }

  update(upperCamelClazz: IUpperCamelClazz): Observable<EntityResponseType> {
    return this.http.put<IUpperCamelClazz>(this.resourceUrl, upperCamelClazz, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUpperCamelClazz>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUpperCamelClazz[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
