import React from 'react'
import { Link } from 'react-router-dom' 
import styles from './styles.module.css'
import { useProduct } from '../../Context/ProductContext'

const Navbar = () => {
  const { categories, setCategory } = useProduct() 

 
  return (
    <> 
      <div className="bg-zinc-900/10 mx-auto h-[1.1px] shadow-sm shadow-zinc-900/10 px-12">
        <div className="flex-1 flex items-center justify-center sm:items-stretch sm:justify-start">
          <div className={styles.logo}>
            <Link className={styles.link} to="/">
              <div className={styles.logoBox}>
              <h1 className={styles.logoText}>LOGO</h1>
              </div>
            </Link>
          </div>
          <div className="hidden sm:block sm:ml-6"></div>
        </div>
      </div>
      <nav className={styles.categoryNav}>
        <div>
          <Link
            className={styles.categoryLink}
            to="/"
            onClick={() => setCategory("")}
          >
            <h1 className="truncate">All</h1>
          </Link>
        </div>
        {categories &&
          categories.map((item, index) => {
            return (
              <div key={`${item.name}-${index}`}>
                <Link
                  className={styles.categoryLink}
                  to={`/${item.name.toLowerCase()}`}
                  onClick={() => {
                    setCategory(item.name.toLowerCase());
                  }}
                >
                  <h1 className="truncate">{item.name}</h1>
                </Link>
              </div>
            );
          })}
      </nav>
      <div className="bg-zinc-900/10 mx-auto h-[1px] shadow-sm shadow-zinc-900/10 px-12"></div>
    </>
  );
};

export default Navbar;
