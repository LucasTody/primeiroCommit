import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from  '@angular/common';

import { /*products,*/ Product } from '../products';
import { ProductsService } from '../product-list/products.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  private product: Product;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private _location: Location
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.loadProduct(+params.get('productId'));
    });
  }

  loadProduct(productId: Number) {
//    this.product = products[productId];
    this.productService.getProduct(productId).subscribe(
      product => this.product = product
    );
  }

  back() {
    this._location.back();
  }

  delete(productToRemove: Product) {
    if (confirm('Deseja realmente remover o produto "' + productToRemove.name + '"?')) {
      this.productService.deleteProduct(productToRemove.id).subscribe(
        product => {
          window.alert('Produto "' + productToRemove.name + '" excluído!');
          this.back();
        }
      );
    }
  }

}
