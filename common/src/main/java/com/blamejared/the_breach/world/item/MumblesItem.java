package com.blamejared.the_breach.world.item;

import com.blamejared.the_breach.TBConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.stream.IntStream;

public class MumblesItem extends Item {
    
    public MumblesItem(final Properties properties) {
        
        super(properties);
    }
    
    public static final List<String> MUMBLES = List.of(
            "the Trees... they provide the Bright",
            "the Bright... it creates all",
            "four... for wood",
            "eight... for stone",
            "the Bright is there... what's missing... why won't it open",
            "the Bright... it spreads",
            "the Bright was kinder to me... once",
            "the Spread... it used to feel good",
            "the Bright... it called to me",
            "I am so tired...",
            "the Bright... the Trees...",
            "eight Bright... holding the Spread in place",
            "Bright on the outside... Spread in the center",
            "the frame... where is the frame...",
            "the Bright held it together... the Spread filled the space",
            "there was a way back...",
            "the Bright... it can open the way",
            "it was supposed to take me home...",
            "the way out... made from what brought me here",
            "Bright for the frame... Spread for the key",
            "the Spread... it must be surrounded",
            "why did the Spread break... did I need a tool...",
            "the Spread crumbles in my hands... it needs to be cut"
    );
    
    public static final List<MutableComponent> MUMBLE_COMPONENTS = IntStream.range(0, MUMBLES.size())
            .mapToObj(value -> Component.translatable(TBConstants.sid("bleak.mumble." + value)).withStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.DARK_GRAY)))
            .toList();
    
    
    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        
        ItemStack itemStack = player.getItemInHand(hand);
        if(level instanceof ServerLevel serverLevel) {
            player.sendOverlayMessage(MUMBLE_COMPONENTS.get(serverLevel.getRandom().nextInt(MUMBLE_COMPONENTS.size())));
            itemStack.consume(1, player);
        }
        return InteractionResult.SUCCESS;
    }
    
}
