package WebEcommerce.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import WebEcommerce.Connection.DBConnection;
import WebEcommerce.Dao.StoreDao;
import WebEcommerce.Model.StoreModel;
import WebEcommerce.Model.UserModel;

public class StoreDaoImpl extends DBConnection implements StoreDao {

    @Override
    public List<StoreModel> getAllStore() {
        List<StoreModel> stores = new ArrayList<StoreModel>();
        String sql = "SELECT * FROM store";
        try {
            Connection conn = super.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StoreModel store = new StoreModel();
                store.set_id(rs.getInt("_id"));
                store.setName(rs.getString("name"));
                store.setBio(rs.getString("bio"));
                store.setSlug(rs.getString("slug"));
                store.setOwnerId(rs.getInt("ownerId"));
                store.setStaffIds(rs.getString("staffIds"));
                store.setIsActive(rs.getBoolean("isActive"));
                store.setIsOpen(rs.getBoolean("isOpen"));
                store.setAvatar(rs.getString("avatar"));
                store.setCover(rs.getString("cover"));
                store.setFeatured_images(rs.getString("featured_images"));
                store.setCommissionId(rs.getInt("commissionId"));
                store.setPoint(rs.getInt("point"));
                store.setRating(rs.getInt("rating"));
                store.setE_wallet(rs.getInt("e_wallet"));
                store.setCreatedAt(rs.getDate("createdAt"));
                store.setUpdatedAt(rs.getDate("updatedAt"));
                stores.add(store);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stores;
    }

    @Override
    public void insertStore(StoreModel store) {
        String sql = "INSERT INTO store (name,bio,slug,ownerId,staffIds,isActive,isOpen,avatar,cover,featured_images,commissionId,point,rating,e_wallet,createdAt,updatedAt) VALUES "
                + "(?,?,?,?,?,1,1,?,?,?,?,0,3,0,?,?)";
        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, store.getName());
            ps.setString(2, store.getBio());
            ps.setString(3, store.getName());
            ps.setString(4, "1");
            ps.setString(5, null);
            ps.setString(6, store.getAvatar());
            ps.setString(7, store.getCover());
            ps.setString(8, store.getFeatured_images());
            ps.setString(9, null);
            ps.setDate(10, (java.sql.Date) store.getCreatedAt());
            ps.setDate(11, (java.sql.Date) store.getUpdatedAt());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public StoreModel getStoreById(int id) {
        StoreModel store = new StoreModel();
        String sql = "SELECT * FROM economies.store where _id = ?";
        try {
            Connection conn = super.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                store.set_id(rs.getInt("_id"));
                store.setName(rs.getString("name"));
                store.setBio(rs.getString("bio"));
                store.setSlug(rs.getString("slug"));
                store.setOwnerId(rs.getInt("ownerId"));
                store.setStaffIds(rs.getString("staffIds"));
                store.setIsActive(rs.getBoolean("isActive"));
                store.setIsOpen(rs.getBoolean("isOpen"));
                store.setAvatar(rs.getString("avatar"));
                store.setCover(rs.getString("cover"));
                store.setFeatured_images(rs.getString("featured_images"));
                store.setCommissionId(rs.getInt("commissionId"));
                store.setPoint(rs.getInt("point"));
                store.setRating(rs.getInt("rating"));
                store.setE_wallet(rs.getInt("e_wallet"));
                store.setCreatedAt(rs.getDate("createdAt"));
                store.setUpdatedAt(rs.getDate("updatedAt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return store;
    }

    @Override
    public void editStore(StoreModel store) {
        String sql = "UPDATE store SET name = ?,slug = ?, bio = ?, avatar = ?, cover = ?, featured_images = ?, updatedAt = ? WHERE _id = 6";
        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, store.getName());
            ps.setString(2, store.getName());
            ps.setString(3, store.getBio());
            ps.setString(4, store.getAvatar());
            ps.setString(5, store.getCover());
            ps.setString(6, store.getFeatured_images());
            ps.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStore(int id) {

    }


    @Override
    public List<StoreModel> search(String query, int size, int index) {
        List<StoreModel> stores = new ArrayList<StoreModel>();
        String sql = "with x as(select *,row_number() over(order by createdAt desc)as r from economies.store where name like '%" + query + "%')\n" +
                "select * from x where r between ?*?-? and ?*?";
        try {
            Connection conn = super.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,index);
            ps.setInt(2,size);
            ps.setInt(3,size-1);
            ps.setInt(4,index);
            ps.setInt(5,size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StoreModel store = new StoreModel();
                store.set_id(rs.getInt("_id"));
                store.setName(rs.getString("name"));
                store.setBio(rs.getString("bio"));
                store.setSlug(rs.getString("slug"));
                store.setOwnerId(rs.getInt("ownerId"));
                store.setStaffIds(rs.getString("staffIds"));
                store.setIsActive(rs.getBoolean("isActive"));
                store.setIsOpen(rs.getBoolean("isOpen"));
                store.setAvatar(rs.getString("avatar"));
                store.setCover(rs.getString("cover"));
                store.setFeatured_images(rs.getString("featured_images"));
                store.setCommissionId(rs.getInt("commissionId"));
                store.setPoint(rs.getInt("point"));
                store.setRating(rs.getInt("rating"));
                store.setE_wallet(rs.getInt("e_wallet"));
                store.setCreatedAt(rs.getDate("createdAt"));
                store.setUpdatedAt(rs.getDate("updatedAt"));
                stores.add(store);
            }
            System.out.println(stores.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return stores;
    }
    @Override
    public UserModel getOwnerStore() {
        UserModel owner=new UserModel();
        String sql = "SELECT user._id, user.fistName, user.lastName, user.address, user.avatar, user.phone, user.email FROM store inner join user on store.ownerId = user._id where store._id = 6";
        try {
            Connection conn = super.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                owner.set_id(rs.getInt("_id"));
                owner.setFirstName(rs.getString("fistName"));
                owner.setLastName(rs.getString("lastName"));
                owner.setAddress(rs.getString("address"));
                owner.setAvatar(rs.getString("avatar"));
                owner.setPhone(rs.getString("phone"));
                owner.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return owner;
    }

    @Override
    public int countStaffInStore() {
        String sql = "SELECT count(staffIds) FROM store where _id=6";
        try {
            Connection conn = super.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateStaffStore(int id) {
        String sql = "UPDATE store SET staffIds = ?, updatedAt = ? WHERE _id = 6";
        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public UserModel getStaff() {
        UserModel staff=new UserModel();
        String sql = "SELECT user._id, user.fistName, user.lastName, user.address, user.phone, user.email FROM store inner join user on store.staffIds = user._id where store._id = 6";
        try {
            Connection conn = super.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                staff.set_id(rs.getInt("_id"));
                staff.setFirstName(rs.getString("fistName"));
                staff.setLastName(rs.getString("lastName"));
                staff.setAddress(rs.getString("address"));
                staff.setPhone(rs.getString("phone"));
                staff.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public void Ruttien(int sotien) {
        String sql = "UPDATE economies.store SET economies.store.e_wallet = economies.store.e_wallet - ?, updatedAt = ?  WHERE economies.store._id=6";
        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sotien);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
