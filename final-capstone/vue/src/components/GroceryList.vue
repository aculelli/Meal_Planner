<template>
  <div>
    <h1>Grocery List</h1>
    <br />
    <div id="grocery-search">
      <br />

      <form
        id="add-new-ingredient-to-grocery-list"
        v-on:submit.prevent="createGroceryItem()"
      >
        <input type="text" v-model="newItem" placeholder="Add new ingredient" />
        <button type="submit" class="btn-save">Save</button>
      </form>
    </div>
    <div class="grocery-list-table">
      <table>
        <th id="gr-list-header">To Buy</th>
        <tr>
          <div id="search-div">
            <td id="search-row">
              <label id="search-items" for="search-field">Search Items </label>
              <input
                id="search-field"
                class="filter"
                type="text"
                v-model="filterItems"
                placeholder="search items"
              />
            </td>
          </div>
        </tr>
        <tr
          v-for="item in filteredItems"
          v-bind:key="item.name"
          v-bind:class="item.done ? 'checked' : 'unchecked'"
        >
          <input
            type="checkbox"
            v-model="item.done"
            @change="moveItem(item)"
          /><label>
            {{ item.name }}

            <span id="delete" v-on:click="deleteGroceryItem(item)">
              ×</span
            ></label
          >
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import mealPlanService from "../services/MealPlanService.js";
export default {
  data() {
    return {
      filterItems: "",
      newItem: "",
      groceryItems: [],
    };
  },
  methods: {
    deleteGroceryItem(itemToDelete) {
      this.groceryItems = this.groceryItems.filter((item) => {
        return item !== itemToDelete;
      });
    },

    createGroceryItem() {
      const newItemObject = { name: this.newItem, done: false };
      this.groceryItems.unshift(newItemObject);
      this.newItem = "";
    },

    moveItem(item) {
      if (item.done === true) {
        this.deleteGroceryItem(item);
        this.groceryItems.push(item);
      } else {
        this.deleteGroceryItem(item);
        this.groceryItems.unshift(item);
      }
    },
  },
  computed: {
    filteredItems() {
      return this.groceryItems.filter((item) => {
        return item.name.includes(this.filterItems);
      });
    },
  },
  created() {
    mealPlanService
      .getIngredientsByMealPlanId(this.$route.params.mealPlanId)
      .then((ingredient) => {
        this.groceryItems = ingredient.data;
      });
  },
};
</script>

<style scoped>
.btn-save {
  background-color: #1a4314;
  width: 100px;
  height: 30px;
  padding: 0;
  margin-left: 10px;
}
#search-items {
  margin-right: 10px;
  background-color: #1a4314;
  color: white;
  padding-left: 10px;
}

#grocery-search {
  background-color: #94c973;
  border-radius: 25px;
  width: 500px;
  margin: 0 auto;
}

.unchecked {
  text-align: left;
}

.checked {
  background-color: rgb(155, 153, 153);
  text-decoration: line-through;
  text-align: left;
}

#delete {
  color: red;
  cursor: pointer;
  text-decoration: none;
}

#add-new-ingredient-to-grocery-list {
  padding-bottom: 20px;
  margin: 0;
}

.grocery-list-table {
  width: 496px;
  margin: auto;
}

input[type="checkbox"] {
  position: absolute;
}

input[type="checkbox"] ~ label {
  padding-left: 1.4em;
  display: inline-block;
  border-radius: 15px;
  text-align: left;
  padding-right: 5px;
  padding: 5px;
  margin-left: 25px;
}

#search-row {
  border: 5px dotted;
  border-right: none;
  border-left: none;
  border-top: none;
}

#search-div {
  margin: auto;

  align-content: center;
  align-items: center;
  justify-content: center;
  justify-items: center;
  vertical-align: middle;
  margin-left: auto;
  margin-right: auto;
  float: middle;
  text-align: center;
  padding-left: 55px;
}
</style>