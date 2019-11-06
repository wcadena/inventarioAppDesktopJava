<template>
  <div class="shop-wrapper">
		<page-title-bar></page-title-bar>
    <v-container fluid px-4 py-0 grid-list-xl>
       <ais-instant-search 
        :search-client="searchClient"
        index-name="ikea"
       >
          <v-layout row wrap>
				   <v-flex xl9 lg9 md9 sm12 xs12>
              <product-items></product-items>  
            </v-flex>  
            <v-flex xl3 lg3 md3 sm0 xs0 class="shop-filter">
              <sidebar-filters></sidebar-filters>
            </v-flex>
          </v-layout>  
       </ais-instant-search>
    </v-container>   
  </div>
</template>
<script>
import ProductItems from "Components/Shop/ProductItems";
import SidebarFilters from "Components/Shop/SidebarFilters";

import algoliasearch from 'algoliasearch/lite';
import 'instantsearch.css/themes/algolia-min.css';

const searchClient = algoliasearch('latency', '6be0576ff61c053d5f9a3225e2a90f76');

export default {
  components: {
    ProductItems,
    SidebarFilters
  },
  data(){
    return {
      searchClient,
    };
  },
  computed: {
    ecommerceSidebarFilter: {
      get() {
        return this.$store.getters.ecommerceSidebarFilter;
      },
      set(val) {
        this.$store.dispatch("toggleEcommerceSidebarFilter", val);
      }
    }
  }
};
</script>
