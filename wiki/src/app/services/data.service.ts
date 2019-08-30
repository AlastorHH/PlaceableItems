import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class DataService {
  constructor(private http: HttpClient) {}

  public getMetadata(): Promise<any> {
    return this.http.get("./assets/data.json").toPromise()
  }

  public getModel(name: string): Promise<any> {
    return this.http.get("./assets/models/" + name + ".json").toPromise().then(data => {
      data["texture"] = "./assets/textures/" + name + ".png";
      if(data["credit"] == null)
        data["credit"] = data["__comment"];
      return data;
    });
  }
}
