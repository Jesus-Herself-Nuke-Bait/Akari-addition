package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMAmberDoggyFucked;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPositionBipeds;
import com.lilithsthrone.game.sex.positions.SexSlot;
import com.lilithsthrone.game.sex.positions.SexSlotBipeds;
import com.lilithsthrone.game.sex.sexActions.dominion.AmberSpecials;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.3.4
 * @author Innoxia
 */
public class Amber extends NPC {

	public Amber() {
		this(false);
	}
	
	public Amber(boolean isImported) {
		super(isImported, new NameTriplet("Amber"), "Acerbi",
				"The highest-ranking of Zaranix's maids, Amber is clearly outraged by the fact that you're wandering around her master's house unsupervised.",
				39, Month.OCTOBER, 17,
				12, Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, true);
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceDifferenceToAppearAsAge(28);
		}
		if(this.getBodyMaterial()!=BodyMaterial.FLESH) {
			this.setBodyMaterial(BodyMaterial.FLESH);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.equipClothing(null);
			this.resetPerksMap();
		}
	}

	@Override
	public void setupPerks() {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.UNARMED_TRAINING),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 2)));
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:

		if(setPersona) {
			this.setPersonality(Util.newHashMapOfValues(
					new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
					new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
					new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MAID);
	
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_SADIST);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
			this.addFetish(Fetish.FETISH_FOOT_GIVING);
		}
		
		// Body:
		// Add full body reset as this method is called after leaving Zaranix's house:
		this.setAgeAppearanceDifferenceToAppearAsAge(28);
		this.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.DEMON, RaceStage.GREATER);
		this.setTailType(TailType.DEMON_HAIR_TIP);
		this.setWingType(WingType.NONE);
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.SWEPT_BACK);

		// Core:
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, CoveringPattern.NONE, Colour.EYE_AMBER, true, Colour.EYE_AMBER, true));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_EBONY), true);
		this.setSkinCovering(new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, Colour.SKIN_EBONY, false, Colour.COVERING_AMBER, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, Colour.SKIN_EBONY, false, Colour.COVERING_AMBER, true), false);
		this.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, Colour.COVERING_AMBER, true, Colour.COVERING_AMBER, true), false);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_BLACK), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, CoveringPattern.NONE, Colour.COVERING_AMBER, true, Colour.COVERING_AMBER, true), true);
		this.setHairLength(HairLength.FIVE_ABOVE_ASS.getMedianValue());
		this.setHairStyle(HairStyle.WAVY);
		
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, CoveringPattern.NONE, Colour.COVERING_AMBER, true, Colour.COVERING_AMBER, true), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, Colour.COVERING_AMBER));
		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, Colour.COVERING_AMBER));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, Colour.COVERING_BLACK));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, Colour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, Colour.COVERING_BLACK));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.G.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(false);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.FOUR_WOMANLY);
		// Anus settings and modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenisGirth.FOUR_FAT);
		this.setPenisSize(25);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(550);
		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		this.setVaginaWetness(Wetness.FOUR_SLIMY);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		
		this.unequipAllClothingIntoVoid(true, true);
		
		this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.FIRE));
		this.equipOffhandWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.FIRE));
		
		// Tattoos
		// Scars

		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, Colour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, Colour.CLOTHING_RED_DARK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.STOMACH_UNDERBUST_CORSET, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_BLACK, false), true, this);

	}

	@Override
	public String getName(boolean applyNameAlteringEffects) {
		if(!playerKnowsName) {
			return "Fiery Maid";
			
		} else {
			return "Amber";
		}
	}
	
	@Override
	public String getDescription() {
		if(!playerKnowsName) {
			return "This fiery maid is clearly outraged by the fact that you're wandering around her master's house unsupervised";
			
		} else {
			return "The highest-ranking of Zaranix's maids, Amber is one of the most striking succubi you've ever seen."
					+ " Her amber hair and eyes, from which she gets her name, glow with a brilliant luminosity, providing a stark contrast to her jet-black ebony skin."
					+ "<br/>"
					+ "Amber is ruthlessly sadistic, and delights in imposing her dominance over her subordinates.";
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#FFB38A";
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex() {
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_BLACK, false), true, this);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat:
	
	@Override
	public String getMainAttackDescription(GameCharacter target, boolean isHit) {
		return "<p>"
					+ UtilText.parse(target,
							UtilText.returnStringAtRandom(
							"Amber's eyes burn with an incandescent fury as she delivers a kick straight into [npc.namePos] side!",
							"With a furious cry, Amber punches [npc.name] square in the chest!",
							"Spitting curses, Amber furiously kicks at [npc.namePos] shins!",
							"Amber's hair, burning with the same fiery fury as her eyes, swishes through the air as she spins to one side and delivers a solid punch to [npc.namePos] [npc.arm]!"))
				+ "</p>";
	}
			
	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Letting out a wild scream, Amber thrusts her arm into mid air as she casts a spell!",
						"Spitting curses, Amber locks her blazing eyes onto yours, before casting a spell!",
						"With an angry curse, Amber steps forwards and casts a spell!") 
			+ "</p>";
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
			if (victory) {
				return new Response("", "", ZaranixHomeGroundFloorRepeat.COMBAT_VICTORY);
			} else {
				return new Response("", "", ZaranixHomeGroundFloorRepeat.COMBAT_LOSS);
			}
			
		} else {
			if (victory) {
				return new Response("", "", AFTER_COMBAT_VICTORY) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixAmberSubdued, true);
					}
				};
			} else {
				return new Response("", "", AFTER_COMBAT_DEFEAT);
			}
			
		}
	}
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "Amber staggers back, bracing herself against the wall as she lets out one last hateful curse,"
						+ " [amber.speech(You... "+(Main.game.getPlayer().isFeminine()?"bitch...":"bastard...")+")]"
					+ "</p>"
					+ "<p>"
						+ "It's quite clear that she's unable to continue fighting any longer, and after just a moment, the effects of your powerful arcane aura make themselves known."
						+ " Amber's angry scowl trails off into a exceptionally lewd moan, and, averting the gaze of her luminescent eyes from you for the first time, she looks down at her groin and slips a hand under her dress."
					+ "</p>"
					+ "<p>"
						+ "[amber.speech(~Aaah!~ Fuck...)]"
						+ " she moans, the movement of her arm making it quite clear that she's fingering herself."
					+ "</p>"
					+ "<p>"
						+ "Despite the noise of your fight, there's no sign of the other maid whose voice you heard upon entering the house."
						+ " Given the chance to have some fun with this angry, and now very horny, maid, you wonder if you should take it, or continue on your way to find Zaranix."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_ENTRANCE.getDialogue(false)) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getPlayer().getLocationPlace().getDialogue(false);
					}
				};
				
			} if(index==2) {
				return new ResponseSex("Use Amber",
						"Have some fun with this fiery maid.",
						true,
						false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
						null,
						null),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "It doesn't look like any of the other maids of the household are coming to help her, so you decide to take this opportunity to have a little fun with Amber."
							+ " Stepping over to where she's leaning against the wall, you reach forwards and take hold of her arm, before pulling her hand out from under her dress."
							+ " Denied the freedom to get herself off, Amber pitifully looks up into your eyes, and instead of fury, you see them filled with burning lust."
						+ "</p>"
						+ "<p>"
							+ "She pushes herself off from the wall, wrapping her arms around your back and desperately pressing her [amber.lips+] against yours."
							+ " You reciprocate the gesture, and after spending a few moments of sliding your tongues into one another's mouths, you pull back, grinning..."
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"Amber's fiery personality is seriously turning you on. You can't bring yourself to take the dominant role, but you <i>do</i> want to have sex with her. Perhaps if you submitted, she'd be willing to fuck you?",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Amber.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "Despite her currently-defeated state, you find yourself incredibly turned on by Amber's dominant, fiery personality."
							+ " Not willing to take the dominant role, but with a deep desire to have sex with the now-very-horny succubus, you walk up to where she's leaning against the wall, and sigh,"
							+ " [pc.speech('Miss Amber' was it? Erm... If you're feeling a little horny, perhaps you could use me? I mean, I-)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that you're a stranger in her master's house, Amber looks up at you with an intense, burning passion in her eyes."
							+ " Sliding her hand out from under her dress, she pushes herself off of the wall, interrupting your sentence as she grabs your head and pulls you into a desperate kiss."
						+ "</p>"
						+ "<p>"
							+ "You reciprocate the gesture, but only spend a few moments sliding your tongues into one another's mouths before Amber pulls back, moaning,"
							+ " [amber.speech(Good bitch! Fuck... I'm so fucking horny! I <i>need</i> you!)]"
						+ "</p>");
				
			} else if (index == 4) {
				return new Response("Transformations",
						"Get Amber to use [amber.her] demonic powers to transform [amber.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Amber.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("Continue", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Sex.getNumberOfOrgasms(Main.game.getNpc(Amber.class)) >= Main.game.getNpc(Amber.class).getOrgasmsBeforeSatisfied()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Amber lets out a deeply satisfied sigh, before sinking to the floor in total exhaustion."
							+ " Despite her fatigue, you see one of her hands slip down between her legs, and, quite clearly still overwhelmed by lust, she starts masturbating in front of you."
							+ " She's obviously not going to pose much of a threat like this, so you turn your attention back towards the task at hand; that of finding Zaranix and rescuing Arthur."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "Amber lets out a desperate whine, before sinking to the floor and pressing both of her hands to her groin."
							+ " Having not been satisfied, she's quite clearly still overcome by her intense lust, and starts frantically masturbating right there on the floor."
							+ " She's obviously not going to pose much of a threat like this, so you turn your attention back towards the task at hand; that of finding Zaranix and rescuing Arthur."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
							+ "Conscious of the fact that there are other maids to look out for, you prepare to set off further into the house..."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", Main.game.getPlayer().getLocationPlace().getDialogue(false)) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getPlayer().getLocationPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeated", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "The fiery-haired maid proves to be too much for you to handle, and, unable to continue fighting, you stumble back against a nearby wall."
						+ " Amber's mocking laughter rings out as she sees that you're defeated, and, stepping towards you, she growls,"
						+ " [amber.speech(You stupid fucking bitch! Now you're going to <i>really</i> pay!)]"
					+ "</p>"
					+ "<p>"
						+ "You look up to see the burning amber eyes of the angry succubus glaring into yours, and before you can do or say anything, she reaches up with one hand and firmly grabs you by the neck."
						+ " You let out a coughing splutter as her grip tightens, which only serves to make her let out another mocking laugh,"
						+ " [amber.speech(Hahaha! Pathetic!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she suddenly hurls you to the floor, and you let out a cry as you fall down onto all fours."
						+ " Amber steps around behind you, and as you try to crawl away, she delivers a stinging sharp slap to your rear end,"
						+ " [amber.speech(Stupid bitch! You've got me all worked up now! Time to teach you a lesson!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Amber starts fucking you.",
						false, false,
						new SMAmberDoggyFucked(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Amber.class), SexSlotBipeds.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.DOGGY_ON_ALL_FOURS))),
						null,
						null, AFTER_SEX_DEFEAT, "<p>"
							+ "Amber takes a firm grasp of your hips, before roughly lifting your ass a little higher."
							+ " The sharp slap of her hand across your right cheek causes you to let out a little cry, which is met by the maid's aggressive growl,"
							+ " [amber.speech(Squeal all you want bitch, <i>you're mine now!</i>)]"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("Used", "", true) {

		@Override
		public String getContent() {
			return "<p>"
						+ "You collapse to the floor, totally exhausted from Amber's rough treatment of you."
						+ " With a final, scornful sneer, Amber stands up,"
						+ " [amber.speech(That's all bitches like you are good for! Now get the fuck out of this house!)]"
					+ "</p>"
					+ "<p>"
						+ "Reaching down to grab "+(Main.game.getPlayer().getHairRawLengthValue()>4?"a fistful of your [pc.hair]":"you by the back of the neck")+", Amber mercilessly drags you to the front door."
						+ " Yanking it open, she spits one last hateful remark your way, before literally kicking you out into the street and slamming the door behind you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Amber throws you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public int getLootMoney() {
		return 5000;
	}
	
	// Sex:
	
	@Override
	public List<Class<?>> getUniqueSexClasses() {
		return Util.newArrayListOfValues(AmberSpecials.class);
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Sex.getSexManager().getPosition() == SexPositionBipeds.DOGGY_STYLE) {
			if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
			} else {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS);
			}
		}
		
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Sex.getSexManager().getPosition() == SexPositionBipeds.DOGGY_STYLE) {
			if(target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) && target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			} else {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			}
		}

		return super.getMainSexPreference(target);
	}

	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		return slot==SexSlotBipeds.DOGGY_BEHIND;
	}
	
	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
}