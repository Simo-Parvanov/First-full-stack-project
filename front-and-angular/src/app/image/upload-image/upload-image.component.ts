import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ImageService} from 'src/app/services/image.service';
import {Router} from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {

  @ViewChild('imageInputFile', {static: false}) imageFile: ElementRef;

  image: File;
  myImage: File;

  constructor(
    private imageService: ImageService, private route: Router, private spinner: NgxSpinnerService) { }

  ngOnInit() {

  }
  onFileChange(event) {
    this.image = event.target.files[0];
    const  fr = new FileReader();
    fr.onload = (meEvent: any) => {
      this.myImage = meEvent.target.result;
    };
    fr.readAsDataURL(this.image);
  }

  onUpload(): void {
    this.spinner.show();
    this.imageService.upload(this.image).subscribe(
      data => {
        this.spinner.hide();
        this.route.navigate(['/mod']);
        this.reset();
      },
      error => {
        this.spinner.hide();
        alert(error.error.message);
        this.reset();
      }
    );
  }

  reset(): void {
    this.image = null;
    this.myImage = null;
    this.imageFile.nativeElement.value = '';
  }

}
