import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms'

import { Product } from '../products';
import { ProductsService } from './products.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  
  private newProduct : /*any*/Product;

  private products : Product[];

  constructor(
    private productService: ProductsService
  ) {  }

  ngOnInit() {
    this.newProduct = new /*Object*/Product();
    this.getProducts();
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
//      this.productService.save(this.newProduct);
      this.productService.createProduct(this.newProduct).subscribe(
        id => {
          this.newProduct = new /*Object*/Product();
          this.getProducts();
        }
      );
    }
  }

  getProducts(): void {
    //this.products = this.productService.getProducts();
    this.productService.getProducts().subscribe(
      products => this.products = products);
  }

  share() {
    window.alert('The product has been shared!');
  }
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/