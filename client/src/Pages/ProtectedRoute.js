import React from 'react'

const ProtectedRoute = () => {
  return (
    <Route
    {...rest}
    render={(props) => {
      <Redirect to={{ pathname: "/" }} />;
    }}
  />
  )
}

export default ProtectedRoute
