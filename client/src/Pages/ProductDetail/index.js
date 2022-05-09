import { useEffect } from "react";
import { useParams } from "react-router-dom"; 
import Spinner from "../../Components/Spinner";
import { useProduct } from "../../Context/ProductContext";
import styles from "./styles.module.css";

const ProductDetail = () => { 
  
  const { product, loading, setProductID } = useProduct();
 

  const { product_id } = useParams();

  useEffect(() => {
    setProductID(product_id);
  }, []);

  return (
    <>
      {!loading && product?.id ? (
          <div className="flex flex-wrap max-w-7xl mx-auto my-4">
            <div className="w-full sm:w-2/2 md:w-2/2 xl:w-5/5 p-4 flex flex-wrap">
              <img
                alt="ecommerce"
                className={styles.image}
                src={product.image}
              />
              <div className="lg:w-2/3 w-full lg:pl-10 lg:py-6 my-auto">
                <h2 className={styles.brand}>
                  Category
                </h2>
                <h1 className="text-gray-900 text-2xl font-bold tracking-tight mb-1">
                  {product.category.name}
                </h1>
                <h2> {product.name} </h2>
                <p className={styles.productDetailText}>
                  {product.description}
                </p>
                <div className="flex">
                  <div className="my-auto">
                    <span className="font-extralight text-2xl inline-block align-middle mt-2 my-auto">
                    € {product.europrice}
                    </span>
                  </div> 
                </div>
              </div>
            </div>
          </div>
      ) : (
        <Spinner />
      )}
    </>
  );
};

export default ProductDetail;
