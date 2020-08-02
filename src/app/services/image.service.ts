import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Image} from 'src/app/components/models/image';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  imageUrl = 'http://localhost:8080/cloudinary/';

  constructor( private httpClient: HttpClient) { }

  public list(): Observable<Image[]> {
    return this.httpClient.get<Image[]>(this.httpClient + 'list');
  }

  public upload(image: File): Observable<any> {
    const formData = new FormData();
    formData.append('multipartFile', image );
    return this.httpClient.post<any>(this.imageUrl + 'upload', formData);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete<any>(this.imageUrl + 'delete/${id}');
  }
}
