import axios from 'axios'
import { createContext, useContext, useEffect, useState } from 'react'
const ProductContext = createContext()


export const ProductProvider = ({ children }) => {
  const [productList, setProductList] = useState([])
  const [categories, setCategories] = useState()
  const [category, setCategory] = useState("/products")
  const [productID, setProductID] = useState("")
  const [product, setProduct] = useState({})
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    setLoading(true)
    const getCategories = async () => {
      let categoriesData
      await axios("http://localhost:8888/api/v1/categories/all").then(
        (res) => setCategories(res.data)      
      )
    }
    getCategories()
    setLoading(false)
  }, [])

  useEffect(() => {
    setLoading(true)
    const getProductData = async () => {
      await axios.get(`http://localhost:8888/api/v1/products/all`).then((res) => {
        console.log("our products ::", res);
        setProductList(res.data) 
        setLoading(false)
      })
    }
    getProductData()
  }, [category])


  useEffect(() => {
    setLoading(true)
    const getProductDetail = async () => {   
      productID && productID.length > 0 && await axios.get(`http://localhost:8888/api/v1/products/${productID}`).then(
        (res) => {
          setProduct(res.data)
          setLoading(false)
        }
      )
    }
    getProductDetail()
  }, [productID])

  const values = {
    product,
    productList,
    productID,
    setProductID,
    categories,
    setCategory,
    loading,
  }

  return (
    <ProductContext.Provider value={values}>{children}</ProductContext.Provider>
  )
}

export const useProduct = () => useContext(ProductContext)
