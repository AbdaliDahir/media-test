import React, {useEffect} from "react";
import { useProduct } from "../../Context/ProductContext";
import styles from "./styles.module.css";
import Spinner from "../../Components/Spinner";
import { useParams } from "react-router-dom"; 
import Card from "../../Components/Card";

const Products = () => { 

  const { productList, loading, setProductID, setCategory } = useProduct();
  
  const {category_id} = useParams()

  useEffect(() => {
    console.log(productList);
    setCategory(category_id)
  }, [category_id])

  return (
    <div className={styles.cardGroup}>
      {!loading ? (
        productList?.map((item, index) => { 
          return (
            <Card key={`product-${index}`} item={item} setProductID={setProductID} />
          );
        })
      ) : (
        <Spinner />
      )}
    </div>
  );
};

export default Products;
