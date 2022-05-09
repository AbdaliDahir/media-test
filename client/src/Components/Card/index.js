import styles from './styles.module.css'
import { Link } from 'react-router-dom'

const Card = ({
  item,
}) => {

  return (
    <div key={`${item.id}-item`} className={styles.card} title={item.name}>
      <div className={styles.cardLink}> 
        <Link to={`/product/${item.id}`}>
          <div className={styles.cardHeader}>
            <img className={styles.cardImg} src={item.image} alt="" />
          </div>
        </Link>
        <div className={styles.cardBody}>
          <div>
            <h2 className={styles.cardTitle} title={item.name}>
              {item.name}
            </h2>
          </div>
          <div>
            <p className={styles.cardTitle} title={item.category.name}>
              <span className={styles.brand} title="Category">
                Catgory :
              </span>{" "}
              {item.category.name}
            </p>
          </div>
          <div>
            <div className="my-auto" title={`$${item.europrice}`}>
              <span className={styles.priceSub}>€</span>
              <span className={styles.priceTop}>{Math.trunc(item.europrice)}</span>
              {parseInt((item.europrice % 1).toFixed(2).substring(2)) !== 0 ? (
                <span className={styles.priceSub}>
                  {parseInt((item.europrice % 1).toFixed(2).substring(2))}
                </span>
              ) : (
                ""
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Card
