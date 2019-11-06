<template>
	<div class="v-edit-products">
		<app-section-loader :status="loader"></app-section-loader>
		<page-title-bar></page-title-bar>

		<template v-if="selectedProduct !== null">
			<v-container fluid>
				<v-row>
					<v-col cols="col-sm-12 col-md-10 mx-auto">
						<v-row>
							<v-col cols="col-sm-12 col-md-6">
								<v-row  class="product-images-wrap">
									<v-col cols="col-sm-12 col-md-2">
										<div class="thumb-wrap ml-auto" for="upload" v-for="img in selectedProduct.image_gallery">
											<v-img :src="img" style="height: 70px;" @mouseover="doHover(img)" />
											<div class="edit-btn d-flex justify-center align-items-center">
												<v-icon dark>edit</v-icon>
											</div>
											<input type="file" id="upload" accept="image/*" class="upload-img">
										</div>
									</v-col>
									<v-col cols="col-sm-12 col-md-10">
										<v-img :src="selectedProduct.image" style="width: 100%;" />
									</v-col>
								</v-row>
							</v-col>
							<v-col cols="col-sm-12 col-md-6 content-wrap">
								<router-link class="pt-4" :to="`/${getCurrentAppLayoutHandler() + '/ecommerce/edit-product'}`">
									Go to Products</router-link>
								<v-text-field prepend-icon="edit" class="name-input" v-model="selectedProduct.name" required></v-text-field>
								<v-text-field prepend-icon="edit" class="price-input" v-model="selectedProduct.price" required></v-text-field>
								<v-text-field prepend-icon="edit" label="Availablity" v-model="selectedProduct.availablity" required></v-text-field>
								<v-text-field prepend-icon="edit" label="Product Code :" v-model="selectedProduct.product_code" required>
								</v-text-field>
								<v-text-field prepend-icon="edit" label="Tags :" v-model="selectedProduct.tags" required></v-text-field>
								<v-text-field prepend-icon="edit" label="Description" v-model="selectedProduct.description" required></v-text-field>
								<v-text-field prepend-icon="edit" label="Features points" v-model="selectedProduct.features" required></v-text-field>
								<v-text-field prepend-icon="edit" value="5" type="number" label="Total Products"></v-text-field>
								<!-- <v-layout row wrap>
											<v-flex xs3 v-for="color in colors">
												<v-checkbox :label="color"></v-checkbox>
											</v-flex>  
										</v-layout>
										<v-layout row wrap>
											<v-flex xs2 v-for="size in sizes">
												<v-checkbox :label="size"></v-checkbox>
											</v-flex>  
										</v-layout> -->
							</v-col>
						</v-row>
					</v-col>
				</v-row>
			</v-container>
		</template>
	</div>
</template>

<script>
	import { productsData } from 'Views/ecommerce/data.js'
	import { getCurrentAppLayout } from "Helpers/helpers";
	export default{
		data(){
			return{
				loader: false,
				productsData,
				products:'',
				selectedProduct:'',
				colors: ["Red","Blue","Yellow","Green"],
				sizes:["28","30","32","34","36","38","40"]
			}
		},
		mounted(){			
			this.getProductData();
		},
		methods:{
			getProductData(event){
				var allItems = this.productsData.men.concat(this.productsData.women,this.productsData.accessories,this.productsData.gadgets);
				for(var i = 0;i < allItems.length; i++)
				{ if(allItems[i].id == this.$route.params.id )
					{
						this.selectedProduct = {
							availablity: allItems[i].availablity,
							brand: allItems[i].brand,
							category: allItems[i].category,
							category_type: allItems[i].category_type,
							color: allItems[i].color,
							discountPriceValue: allItems[i].discountPriceValue,
							description: allItems[i].description,
							discount_price: allItems[i].discount_price,
							features: allItems[i].features,
							id: allItems[i].id,
							image: allItems[i].image,
							image_gallery: allItems[i].image_gallery,
							name: allItems[i].name,
							popular: allItems[i].popular,
							price: allItems[i].price,
							product_code: allItems[i].product_code,
							quantity: allItems[i].quantity,
							rating: allItems[i].rating,
							status: allItems[i].status,
							tags: allItems[i].tags,
							type: allItems[i].type
						}
					}

				}
			},
			doHover(img){
				// console.log(img)
				this.selectedProduct.image = img;
			},
			getCurrentAppLayoutHandler() {
	         return getCurrentAppLayout(this.$router);
	      }
		}

	}
</script>	
