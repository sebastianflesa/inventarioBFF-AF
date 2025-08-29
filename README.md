# 📌 BFF - Inventario G13

Este BFF expone endpoints REST que redirigen las solicitudes hacia el backend en **Azure App Service**.  
Los endpoints disponibles corresponden a la gestión de **productos** y **bodegas**.

---

## Productos

| Método | Endpoint (BFF)                          | Redirige a (Azure Backend)                                                                 |
|--------|------------------------------------------|---------------------------------------------------------------------------------------------|
| GET    | `/api/productos/getAllProductos`        | [GET All Productos](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/productos) |
| POST   | `/api/productos/createProducto`         | [POST Create Producto](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/productos) |
| POST   | `/api/productos/updateProducto/{id}`    | [POST Update Producto](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/productos/update/{id}) |
| POST   | `/api/productos/deleteProducto/{id}`    | [POST Delete Producto](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/productos/delete/{id}) |

---

## Bodegas

| Método | Endpoint (BFF)                          | Redirige a (Azure Backend)                                                                 |
|--------|------------------------------------------|---------------------------------------------------------------------------------------------|
| GET    | `/api/bodegas/getAllBodegas`            | [GET All Bodegas](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/bodegas) |
| POST   | `/api/bodegas/createBodega`             | [POST Create Bodega](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/bodegas) |
| POST   | `/api/bodegas/updateBodega/{id}`        | [POST Update Bodega](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/bodegas/update/{id}) |
| POST   | `/api/bodegas/deleteBodega/{id}`        | [POST Delete Bodega](https://inventariog13-hdgugedfgmazcxd6.eastus2-01.azurewebsites.net/api/bodegas/delete/{id}) |

---

{
  "nombre": "Laptop",
  "precio": 1200,
  "stock": 15
}
