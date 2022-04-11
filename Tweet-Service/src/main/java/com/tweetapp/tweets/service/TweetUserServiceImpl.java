package com.tweetapp.tweets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.tweets.DTO.MessageDTO;
//import com.tweetapp.tweets.DTO.PasswordResetDTO;
import com.tweetapp.tweets.common.MessageServiceResponse;
import com.tweetapp.tweets.constants.MessageConstants;
import com.tweetapp.tweets.DAO.TweetUserRepository;
import com.tweetapp.tweets.entity.UserEntity;
import com.tweetapp.tweets.exception.ErrorException;

@Service
public class TweetUserServiceImpl implements TweetUserService {

	@Autowired
	public TweetUserRepository userRepository;

	@Autowired
	public MessageServiceResponse messageService;

	public int userIdCount = MessageConstants.USER_ID_COUNT_VALUE;

	@Override
	public MessageDTO saveUserRegisterDetails(UserEntity user) throws ErrorException {
		try {
			boolean userAlreadyExists = false;
			int updateUserId = 0;
			if (!user.getUserName().isEmpty()) {
				List<UserEntity> userList = getALLRegisteredUser();
				for (UserEntity us : userList) {
					updateUserId = us.getUserId();
					if (us.getUserName().equals(user.getUserName())) {
						userAlreadyExists = true;
						break;
					}
					System.out.println("flag - " + userAlreadyExists);
				}
				if (!userAlreadyExists) {
					user.setUserId(updateUserId + userIdCount);
					userRepository.save(user);
					return messageService.messageDetails(MessageConstants.LOGIN_SUCCESSFULLY_MESSAGE, 200);
				} else {
					return messageService.messageDetails(MessageConstants.USER_NAME_ALREADY_EXISTS, 400);
				}
			}

		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.SAVE_USER_REGISTER_DETAILS_METHOD_NAME);
		}
		return messageService.messageDetails(MessageConstants.LOGIN_UNSUCCESSFULLY_MESSAGE, 400);
	}

	@Override
	public List<UserEntity> getALLRegisteredUser() throws ErrorException {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_ALL_REGISTERED_USER);
		}
	}

	@Override
	public MessageDTO saveUserResetPassworDetails(UserEntity password, String username) throws ErrorException {
		try {
			List<UserEntity> userList = getALLRegisteredUser();
			for (UserEntity users : userList) {
				if (users.getUserName().equals(username)) {
					users.setPassword(password.getPassword());
					users.setConfirmPassword(password.getPassword());
					userRepository.save(users);
					return messageService.messageDetails(MessageConstants.LOGIN_SUCCESSFULLY_MESSAGE, 200);
				}
			}
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.SAVE_SERVICE_ERROR,
					MessageConstants.SAVE_USER_RESET_PASSWORD_DETAILS_METHOD_NAME);
		}
		return messageService.messageDetails(MessageConstants.LOGIN_UNSUCCESSFULLY_MESSAGE, 400);
	}

	@Override
	public MessageDTO getLoginUserDetails(UserEntity user) throws ErrorException {
		try {
			List<UserEntity> userList = getALLRegisteredUser();
			for (UserEntity users : userList) {
				if (user.getUserName().equals(users.getUserName())) {
					return messageService.messageDetails(MessageConstants.LOGIN_SUCCESSFULLY_MESSAGE, 200);
				}
			}
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_LOGIN_USER_DETAILS_METHOD_NAME);
		}
		return messageService.messageDetails(MessageConstants.LOGIN_SUCCESSFULLY_MESSAGE, 400);
	}

	@Override
	public Optional<UserEntity> getUserDetailsByName(String username) throws ErrorException {
		try {
			List<UserEntity> userList = getALLRegisteredUser();
			for (UserEntity users : userList) {
				if (username.equals(users.getUserName())) {
					Optional<UserEntity> userIdList = userRepository.findById(users.getUserId());
					return  userIdList;
				}
			}
		} catch (Exception e) {
			throw new ErrorException(e.getMessage(), MessageConstants.FETCH_SERVICE_ERROR,
					MessageConstants.GET_USER_DETAILS_BY_NAME_METHOD_NAME);
		}
		return null;
	}
}
