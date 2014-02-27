package edu.hebtu.movingcampus.subjects;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import edu.hebtu.movingcampus.config.Constants;
import edu.hebtu.movingcampus.entity.CardEntity;
import edu.hebtu.movingcampus.entity.Message;
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.enums.NewsType;
import edu.hebtu.movingcampus.subject.base.ListOfNews;
import edu.hebtu.movingcampus.subject.base.Subject;

public final class LocalMessageSubject extends Subject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Message> list;
	private volatile static CardEntity card;

	public LocalMessageSubject() {
	}

	public CardEntity getCardEntity(){
		synchronized(card){
			return card;
		}
	}

	public void setCardEntity(CardEntity card,Activity context){
		synchronized(card){
			LocalMessageSubject.card=card;
			int left= context.getSharedPreferences(Constants.PREFER_FILE, 0)
					.getInt(Constants.BALANCE_LOWEAST, 10);
			if(card.getCount()<=left){
			}
		}
	}

	@Override
	public Boolean mesureChange(Activity ac) {
//		for (OneofNews s : localSubjects)
//			if (((Subject) s).mesureChange(ac))
//				return true;
		return false;
	}

	public List<Message> dump(Activity context) {
		Message cardnews=new Message();
		cardnews.setTitle("一卡通余额提醒:");
		SharedPreferences pre = context.getSharedPreferences(
				Constants.PREFER_FILE, 0);
		int loweast=pre.getInt(Constants.BALANCE_LOWEAST, 10);
		cardnews.setContent("您的一卡通余额不足"+loweast+"元,请尽快充值"+card.getCount() + " 元");
		
		list.add(cardnews);

		return list;
	}


	@Override
	public String getTag() {
		return "subject." + "0";
	}

	public void setMessageeList(
			List<edu.hebtu.movingcampus.entity.Message> initlist) {
		this.list=initlist;
	}

}
