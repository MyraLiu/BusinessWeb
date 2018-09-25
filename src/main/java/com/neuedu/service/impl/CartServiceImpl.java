package com.neuedu.service.impl;

import com.neuedu.businessconst.Const;
import com.neuedu.common.BigDecimalUtils;
import com.neuedu.common.PropertiesUtils;
import com.neuedu.common.ResponseCode;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.ICartDao;
import com.neuedu.dao.IProductDao;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICartService;
import com.neuedu.vo.CartProductVO;
import com.neuedu.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IProductDao productDao;

    @Override
    public ServerResponse<CartVO> addProductToCart(Integer userid, Integer productId, Integer count) {

        // 1. 将商品添加到购物车，需要进行校验
        // 根据userid  和productid查询购物车
        Cart cart = cartDao.findCartByUseridAndProductid(userid, productId);
        int result;
        // 2. 如果这个产品已经存在购物和
        if (cart == null) {
            // 空添加
            Cart c = new Cart(userid, productId, count, 1);
            result = cartDao.addProductToCart(c);


        } else {
            //更新
            cart.setQuantity(cart.getQuantity() + count);
            result = cartDao.updateProductInCart(cart);

        }

        CartVO cartVO =   getCartVOLimit(userid);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO,ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<CartVO> updateProductInCart(Integer userid, Integer productid, Integer count) {

        // 根据userid  和productid查询购物车
        Cart cart = cartDao.findCartByUseridAndProductid(userid, productid);
        CartVO cartVO = getCartVOLimit(userid);
        if(cart == null){
            return ServerResponse.createServerResponce(ResponseCode.PRODUCT_NOT_IN_CART.getCode(), cartVO,ResponseCode.PRODUCT_NOT_IN_CART.getMsg());
        }
        cart.setQuantity(count);
        cartDao.updateProductInCart(cart);
        cartVO = getCartVOLimit(userid);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO,ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<CartVO> removeProduct(Integer[] productids,Integer userid) {
         // 1.删除商品
        List<Integer> pIdsList  = new ArrayList<>();
        for (Integer id:productids             ) {
            pIdsList.add(id);
        }

        int result = cartDao.removeProduct(pIdsList,userid);
        // 2. 返回商品列表
        if(result>0) {
            CartVO cartVO = getCartVOLimit(userid);
            return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO, ResponseCode.SUCCESS.getMsg());
        }else {
            CartVO cartVO = getCartVOLimit(userid);
            return ServerResponse.createServerResponce(ResponseCode.FAIL.getCode(), cartVO, ResponseCode.FAIL.getMsg());
        }

    }

    @Override
    public ServerResponse<CartVO> checkProduct(Integer productid, Integer userid) {
        // 根据userid  和productid查询购物车
        System.out.println(productid+"===productid==="+userid+"===userid===");
        Cart cart = cartDao.findCartByUseridAndProductid(userid, productid);
       CartVO cartVO = getCartVOLimit(userid);
         if(cart == null){
            return ServerResponse.createServerResponce(ResponseCode.PRODUCT_NOT_IN_CART.getCode(), cartVO,ResponseCode.PRODUCT_NOT_IN_CART.getMsg());
        }

        // 存在商品信息，在设置选中

        cart.setChecked(1);
        cartDao.checkAndUncheck(cart);
        cartVO = getCartVOLimit(userid);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO,ResponseCode.SUCCESS.getMsg());

    }

    @Override
    public ServerResponse<CartVO> uncheckProduct(Integer productid, Integer userid) {
        System.out.println(productid+"===productid==="+userid+"===userid===");

        // 根据userid  和productid查询购物车
        Cart cart = cartDao.findCartByUseridAndProductid(userid, productid);
        CartVO cartVO = getCartVOLimit(userid);
        if(cart == null){
            return ServerResponse.createServerResponce(ResponseCode.PRODUCT_NOT_IN_CART.getCode(), cartVO,ResponseCode.PRODUCT_NOT_IN_CART.getMsg());
        }

        // 存在商品信息，在设置选中

        cart.setChecked(0);
        cartDao.checkAndUncheck(cart);
        cartVO = getCartVOLimit(userid);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO,ResponseCode.SUCCESS.getMsg());

    }

    @Override
    public ServerResponse<CartVO> selectAll(Integer userid) {
        List<Cart> cartList = cartDao.findCartsByUserid(userid);
        if (cartList!=null && cartList.size()>0){
            cartDao.checkAllOrNone(userid,1);
        }
        CartVO cartVO = getCartVOLimit(userid);
          return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO,ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<CartVO> selectNone(Integer userid) {
        List<Cart> cartList = cartDao.findCartsByUserid(userid);
        if (cartList!=null && cartList.size()>0){
            cartDao.checkAllOrNone(userid,0);
        }
        CartVO cartVO = getCartVOLimit(userid);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO,ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<CartVO> listCart(Integer userid) {
        CartVO cartVO = getCartVOLimit(userid);
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(), cartVO,ResponseCode.SUCCESS.getMsg());
    }

    @Override
    public ServerResponse<Integer> sumProducts(Integer userid) {
        int sum = cartDao.sumProducts(userid)     ;
        return ServerResponse.createServerResponce(ResponseCode.SUCCESS.getCode(),sum,ResponseCode.SUCCESS.getMsg());
    }


    /**
     * 返回用户的购物车
     *
     * @param userid
     * @return
     */
    public CartVO getCartVOLimit(Integer userid) {

        CartVO cartVO = new CartVO();
        // 1. 根据userid查询购物车商品集合
        List<Cart> cartList = cartDao.findCartsByUserid(userid);
        // 2. 将cartList change to CartProductVO
        List<CartProductVO> cpList = new ArrayList<>();

        // 购物车总价格
        BigDecimal sum = new BigDecimal(0);


        if (cartList != null && cartList.size() > 0) {
            for (Cart cart : cartList) {
                CartProductVO cartProductVO = new CartProductVO();
                // 2.1 根据商品id查询商品信息
                Product product = productDao.findProductById(cart.getProduct_id());
                cartProductVO.setProductId(product.getId());
                cartProductVO.setName(product.getName());
                cartProductVO.setSubtitle(product.getSubtitle());
                cartProductVO.setMain_image(product.getMain_image());
                cartProductVO.setProductPrice(product.getPrice());
                // 2.2 设置商品总价格
                cartProductVO.setTotalPrice(BigDecimalUtils.multiply(product.getPrice(),
                                                new BigDecimal(cart.getQuantity())));


                // 总价格  选中项求和
                if(cart.getChecked().equals(1)){
                    sum = BigDecimalUtils.add(sum,cartProductVO.getTotalPrice());
                }

                // 2.4 校验库存
                if(product.getStock()>cart.getQuantity()){
                    // 库存充足
                    cartProductVO.setLimitQuantity(Const.STOCK.LIMIT_NUM_SUCCESS.getStockdesc());
                }else {
                    // 库存不足
                    cartProductVO.setLimitQuantity(Const.STOCK.LIMIT_NUM_FAIL.getStockdesc());
                    // 更新购物车中商品的数量   数量限制
//  按照商品id进行更新
                    Cart cart1 = new Cart();
                    cart1.setUser_id(userid);
                    cart1.setQuantity(product.getStock());
                    cart1.setProduct_id(product.getId());
                    cartDao.updateProductInCart(cart1);

                }
                // 2.3 购物车信息
                cartProductVO.setCartId(cart.getId());
                cartProductVO.setUser_id(cart. getUser_id());
                cartProductVO.setStatus(product.getStatus());
                cartProductVO.setChecked(cart.getChecked());
                cartProductVO.setQuantity(cart.getQuantity());
                cartProductVO.setImageHost(PropertiesUtils.getProperty("imageHost"));
               cpList.add(cartProductVO);
            }

        }


        // 购物车列表
        cartVO.setCartProductVOList(cpList);
        // 是否全选
        cartVO.setAllChecked(cartDao.isAllChecked(userid)==0);
        // 总价格
        cartVO.setTotalPrice(sum);
        return cartVO;
    }
}
