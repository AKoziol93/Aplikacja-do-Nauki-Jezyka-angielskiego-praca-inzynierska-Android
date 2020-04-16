package com.koziol.andrzej;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class PictureHelper {
    private final Map<String, Integer> mapPictureRes = new HashMap<>();

    public PictureHelper() {
        mapPictureRes.put("break", R.drawable.break_);
        mapPictureRes.put("brush", R.drawable.brush);
        mapPictureRes.put("buy", R.drawable.buy);
        mapPictureRes.put("climb", R.drawable.climb);
        mapPictureRes.put("close", R.drawable.close);
        mapPictureRes.put("comb", R.drawable.comb);
        mapPictureRes.put("cook", R.drawable.cook);
        mapPictureRes.put("crash", R.drawable.crash);
        mapPictureRes.put("cry", R.drawable.cry);
        mapPictureRes.put("cut", R.drawable.cut);
        mapPictureRes.put("dance", R.drawable.dance);
        mapPictureRes.put("dive", R.drawable.dive);
        mapPictureRes.put("draw", R.drawable.draw);
        mapPictureRes.put("drink", R.drawable.drink);
        mapPictureRes.put("drive", R.drawable.drive);
        mapPictureRes.put("dust", R.drawable.dust);
        mapPictureRes.put("eat", R.drawable.eat);
        mapPictureRes.put("examine", R.drawable.examine);
        mapPictureRes.put("exercise", R.drawable.exercise);
        mapPictureRes.put("fight", R.drawable.fight);
        mapPictureRes.put("fly", R.drawable.fly);
        mapPictureRes.put("fold", R.drawable.fold);
        mapPictureRes.put("give", R.drawable.give);
        mapPictureRes.put("hit", R.drawable.hit);
        mapPictureRes.put("jump", R.drawable.jump);
        mapPictureRes.put("kick", R.drawable.kick);
        mapPictureRes.put("knit", R.drawable.knit);
        mapPictureRes.put("knock", R.drawable.knock);
        mapPictureRes.put("laugh", R.drawable.laugh);
        mapPictureRes.put("lift", R.drawable.lift);
        mapPictureRes.put("listen", R.drawable.listen);
        mapPictureRes.put("look", R.drawable.look);
        mapPictureRes.put("march", R.drawable.march);
        mapPictureRes.put("mix", R.drawable.mix);
        mapPictureRes.put("mop", R.drawable.mop);
        mapPictureRes.put("open", R.drawable.open);
        mapPictureRes.put("pack", R.drawable.pack);
        mapPictureRes.put("paint", R.drawable.paint);
        mapPictureRes.put("pick", R.drawable.pick);
        mapPictureRes.put("plant", R.drawable.plant);
        mapPictureRes.put("play", R.drawable.play);
        mapPictureRes.put("point", R.drawable.point);
        mapPictureRes.put("pull", R.drawable.pull);
        mapPictureRes.put("push", R.drawable.push);
        mapPictureRes.put("read", R.drawable.read);
        mapPictureRes.put("repair", R.drawable.repair);
        mapPictureRes.put("ride", R.drawable.ride);
        mapPictureRes.put("run", R.drawable.run);
        mapPictureRes.put("sail", R.drawable.sail);
        mapPictureRes.put("sell", R.drawable.sell);
        mapPictureRes.put("sew", R.drawable.sew);
        mapPictureRes.put("shave", R.drawable.shave);
        mapPictureRes.put("shout", R.drawable.shout);
        mapPictureRes.put("sing", R.drawable.sing);
        mapPictureRes.put("sit", R.drawable.sit);
        mapPictureRes.put("skate", R.drawable.skate);
        mapPictureRes.put("ski", R.drawable.ski);
        mapPictureRes.put("sleep", R.drawable.sleep);
        mapPictureRes.put("slide", R.drawable.slide);
        mapPictureRes.put("smell", R.drawable.smell);
        mapPictureRes.put("stop", R.drawable.stop);
        mapPictureRes.put("sweep", R.drawable.sweep);
        mapPictureRes.put("swim", R.drawable.swim);
        mapPictureRes.put("swing", R.drawable.swing);
        mapPictureRes.put("talk", R.drawable.talk);
        mapPictureRes.put("teach", R.drawable.teach);
        mapPictureRes.put("tell", R.drawable.tell);
        mapPictureRes.put("throw", R.drawable.throw_);
        mapPictureRes.put("tie", R.drawable.tie);
        mapPictureRes.put("try", R.drawable.try_);
        mapPictureRes.put("vacuum", R.drawable.vacuum);
        mapPictureRes.put("wait", R.drawable.wait);
        mapPictureRes.put("wake", R.drawable.wake);
        mapPictureRes.put("walk", R.drawable.walk);
        mapPictureRes.put("wash", R.drawable.wash);
        mapPictureRes.put("watch", R.drawable.watch);
        mapPictureRes.put("water", R.drawable.water);
        mapPictureRes.put("wave", R.drawable.wave);
        mapPictureRes.put("wipe", R.drawable.wipe);
        mapPictureRes.put("write", R.drawable.write);
    }

    @DrawableRes
    public int getImageRes(String word) {
        return mapPictureRes.get(word);
    }

    /**
     * Losujemy 3 pytanie z czego jedno jest poprawne
     */
    public List<Answer> getRandomChoices(String secretWord) {
        List<Answer> answers = new ArrayList<>();

        answers.add(new Answer(mapPictureRes.get(secretWord), secretWord));

        LinkedList<String> words = new LinkedList<>(mapPictureRes.keySet());
        words.remove(secretWord);

        Collections.shuffle(words);
        String answer1 = words.removeFirst();
        answers.add(new Answer(mapPictureRes.get(answer1), answer1));
        String answer2 = words.removeFirst();
        answers.add(new Answer(mapPictureRes.get(answer2), answer2));

        Collections.shuffle(answers);
        return answers;
    }

    public static class Answer {
        public final int imageId;
        public final String name;

        public Answer(int imageId, String name) {
            this.imageId = imageId;
            this.name = name;
        }
    }
}
