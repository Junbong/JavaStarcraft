package com.bong.starcraft.game.util;


import com.bong.starcraft.StarcraftObject;



/**
 * Created by bong on 15. 6. 16.
 */
public class ObjectUtil {
	public static String newName(Object obj) {
		if (obj != null) {
			if (obj instanceof StarcraftObject) {
				StarcraftObject _gameObj = (StarcraftObject) obj;
				return String.format("%s-%04d",
						_gameObj.getClass().getSimpleName(),
						_gameObj.getObjectId());

			} else {
				return obj.toString();
			}
		}

		return "NullObject";
	}
}
