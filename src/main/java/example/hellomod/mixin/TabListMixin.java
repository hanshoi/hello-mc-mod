package example.hellomod.mixin;

import com.mojang.authlib.GameProfile;
import example.hellomod.HelloMod;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListS2CPacket.class)
abstract class TabListMixin {
    @Mixin(PlayerListS2CPacket.Entry.class)
    private abstract static class EntryMixin {
        @Shadow @Final private GameProfile profile;

        @Inject(method="getDisplayName", at = @At(value = "HEAD"), cancellable = true)
        private void modifyDisplayName(CallbackInfoReturnable<Text> cir) {
            ServerPlayerEntity player = HelloMod.SERVER.getPlayerManager().getPlayer(this.profile.getId());
            int minedDiamonds = player.getStatHandler().getStat(Stats.MINED, Blocks.DIAMOND_BLOCK);
            int minedDirt = player.getStatHandler().getStat(Stats.MINED, Blocks.DIRT);
            System.out.println(this.profile.getName() + " has mined " + minedDiamonds + " diamonds!");
            System.out.println(this.profile.getName() + " has mined " + minedDirt + " dirt!");
            cir.setReturnValue(Text.of(this.profile.getName() + " ").copy().append(Text.of(String.valueOf(minedDirt)).copy().formatted(Formatting.AQUA)));
        }
    }
}
