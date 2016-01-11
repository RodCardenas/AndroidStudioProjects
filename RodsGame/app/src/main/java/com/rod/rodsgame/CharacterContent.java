package com.rod.rodsgame;

import java.util.ArrayList;
import java.util.List;
import chars.Equipment;

import android.content.Context;
import android.graphics.Bitmap;

public class CharacterContent{

	public static List<Character> Characters = new ArrayList<Character>();

	public enum State{
		IDLE,WALKING,ATTACKING,DYING
	}
	public static void addItem(Character player) 
	{
		Characters.add(player);
	}	

	public static class Character {
		public int id;
		public String name;
		public int level;
		public int hp;
		public int str;
		public int def;
		public String character_class;
		public Equipment equips;
		
		public int SPEED; // unit per second
		static final int SIZE = 1; // a unit
		
		State  state = State.IDLE;
		boolean  facingLeft = true;
		public Bitmap playable;
		
		

		public Character(int id, String name, Context c) {
			this.id = id;
			this.name = name;
			this.level = 1;
			this.hp = 10;
			this.str = 10;
			this.def = 10;
			this.character_class = "Classless";
			this.equips = new Equipment(c);
			this.playable =null;
		}
		
		public static void setCharacterClass (Integer id, String charClass){
			Character charTarget = Characters.get(id-1);
			charTarget.character_class = charClass ;			
		}

		@Override
		public String toString() {
			return id + ".\tLevel: " + level + " "+ name;
		}
	
	}
}
