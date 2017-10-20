package tk.mybatis.springboot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.springboot.mapper.UserMapper;
import tk.mybatis.springboot.model.User;
import tk.mybatis.springboot.util.DataGridResult;


@Service("userService")
@Transactional
public class UserServiceImpl {
	
	@Autowired
	private UserMapper userMapper;
	
	public DataGridResult<User> queryUserPageList(Integer page, Integer rows) {
		
		PageHelper.startPage(page, rows);
		List<User> users = userMapper.select(null);
		PageInfo<User> info = new PageInfo<>(users);
		DataGridResult<User> result = new DataGridResult<User>(info.getTotal(), users);
		return result;
		
	}
	
	public void saveUser(User user) {
		user.setCreateAt(new Date());
		user.setUpdateAt(user.getCreateAt());
		userMapper.insertSelective(user);
	}
	
	public void deleteUserByIds(Long[] ids){
		
		List<Object> list = new ArrayList<>();
		Collections.addAll(list, ids);
		Example example = new Example(User.class);
		example.createCriteria().andIn("id", list);
		userMapper.deleteByExample(example);
	}
	
	public User queryUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public void updateUser(User user) {
		user.setUpdateAt(new Date());
		userMapper.updateByPrimaryKeySelective(user);
	}

	
	
	
}
