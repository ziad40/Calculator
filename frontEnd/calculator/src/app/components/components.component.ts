import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-components',
  templateUrl: './components.component.html',
  styleUrls: ['./components.component.css']
})
export class ComponentsComponent implements OnInit {

  onshow:string="";
  whatToSend:string="";
  getanswer:any;
  put(data:string){
    this.onshow=this.onshow+data;
    this.whatToSend=this.onshow.replace("+","&");
  }


  delete(){
    this.onshow=this.onshow.substring(0, this.onshow.length - 1)
    this.whatToSend=this.onshow.replace("+","&");
  }
  change(){
    if(this.onshow.charAt(0)!='-'){
      this.onshow="-"+this.onshow;
      this.whatToSend=this.onshow.replace("+","&");
    }
    else{
      this.onshow=this.onshow.substring(1);
      this.whatToSend=this.onshow.replace("+","&");
    }
  }
  clear(){
    this.onshow="";
    this.whatToSend=this.onshow.replace("+","&");
  }
  private Url="http://localhost:8080/course/output";

  constructor(private http:HttpClient) { }
  getString(mystring:string){
       this.http.get('http://localhost:8080/course/output',{
         responseType:'text',
         params:{
              expression:mystring
         },
         observe:'response'
       }).subscribe(Response=>{
          this.getanswer=Response.body
          this.onshow= this.getanswer
          
       })
  }
  sendrequest(){
      this.getString(this.whatToSend)  ///////////////////
  }
  ngOnInit(): void {
   
  }

}
