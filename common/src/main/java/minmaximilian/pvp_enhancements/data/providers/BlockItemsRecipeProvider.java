package minmaximilian.pvp_enhancements.data.providers;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.simibubi.create.foundation.utility.RegisteredObjects;
import com.tterrag.registrate.util.entry.ItemProviderEntry;

import minmaximilian.pvp_enhancements.PvPEnhancements;
import minmaximilian.pvp_enhancements.block.PvPEnhancementsBlocks;
import minmaximilian.pvp_enhancements.compat.emi.EmiRecipeDefaultsGen;
import minmaximilian.pvp_enhancements.item.PvPEnhancementsItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class BlockItemsRecipeProvider extends PvPRecipeProvider {

    GeneratedRecipe HEPHAESTUS_BAG = create(PvPEnhancementsItems.HEPHAESTUS_BAG)
        .unlockedBy(() -> Items.NETHER_STAR)
        .setEmiDefault()
        .viaShaped(b -> b
            .define('+', Items.NETHER_STAR)
            .define('#', () -> Items.ECHO_SHARD)
            .pattern("###")
            .pattern("#+#")
            .pattern("###")
        );

    GeneratedRecipe WALL_PLASTER = create(PvPEnhancementsBlocks.WALL_PLASTER)
        .unlockedBy(() -> Blocks.GRAVEL)
        .returns(16)
        .setEmiDefault()
        .viaShapeless(b -> b
            .requires(Blocks.GRAVEL)
            .requires(Blocks.SAND)
            .requires(Blocks.CLAY)
        );

    public BlockItemsRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    GeneratedRecipeBuilder create(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(ResourceLocation result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(ItemProviderEntry<? extends ItemLike> result) {
        return create(result::get);
    }

    class GeneratedRecipeBuilder {

        private final String path;
        private String suffix;
        private Supplier<? extends ItemLike> result;
        private ResourceLocation compatDatagenOutput;

        private Supplier<ItemPredicate> unlockedBy;
        private int amount;
        private boolean addToEmiDefaults;

        private GeneratedRecipeBuilder(String path) {
            this.path = path;
            this.suffix = "";
            this.amount = 1;
        }

        public GeneratedRecipeBuilder(String path, Supplier<? extends ItemLike> result) {
            this(path);
            this.result = result;
        }

        public GeneratedRecipeBuilder(String path, ResourceLocation result) {
            this(path);
            this.compatDatagenOutput = result;
        }

        private static ResourceLocation clean(ResourceLocation loc) {
            String path = loc.getPath();
            while (path.contains("//")) {
                path = path.replaceAll("//", "/");
            }
            return new ResourceLocation(loc.getNamespace(), path);
        }

        GeneratedRecipeBuilder returns(int amount) {
            this.amount = amount;
            return this;
        }

        GeneratedRecipeBuilder unlockedBy(Supplier<? extends ItemLike> item) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                .of(item.get())
                .build();
            return this;
        }

        GeneratedRecipeBuilder unlockedByTag(Supplier<TagKey<Item>> tag) {
            this.unlockedBy = () -> ItemPredicate.Builder.item()
                .of(tag.get())
                .build();
            return this;
        }

        GeneratedRecipeBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        GeneratedRecipeBuilder setEmiDefault() {
            return setEmiDefault(true);
        }

        GeneratedRecipeBuilder setEmiDefault(boolean addToEmiDefaults) {
            this.addToEmiDefaults = addToEmiDefaults;
            return this;
        }

        GeneratedRecipe viaShaped(UnaryOperator<ShapedRecipeBuilder> builder) {
            return register(consumer -> {
                ShapedRecipeBuilder b = builder.apply(
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result.get(), amount));
                if (unlockedBy != null) {
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                }
                b.save(consumer, createLocation("crafting"));
            });
        }

        GeneratedRecipe viaShapeless(UnaryOperator<ShapelessRecipeBuilder> builder) {
            return register(consumer -> {
                ShapelessRecipeBuilder b = builder.apply(
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result.get(), amount));
                if (unlockedBy != null) {
                    b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                }
                b.save(consumer, createLocation("crafting"));
            });
        }

        private ResourceLocation createSimpleLocation(String recipeType) {
            ResourceLocation loc = clean(
                PvPEnhancements.asResource(recipeType + "/" + getRegistryName().getPath() + suffix));
            if (addToEmiDefaults) {
                EmiRecipeDefaultsGen.DEFAULT_RECIPES.add(loc);
            }
            return loc;
        }

        private ResourceLocation createLocation(String recipeType) {
            ResourceLocation loc = clean(
                PvPEnhancements.asResource(recipeType + "/" + path + "/" + getRegistryName().getPath() + suffix));
            if (addToEmiDefaults) {
                EmiRecipeDefaultsGen.DEFAULT_RECIPES.add(loc);
            }
            return loc;
        }

        private ResourceLocation getRegistryName() {
            return compatDatagenOutput == null ? RegisteredObjects.getKeyOrThrow(result.get()
                .asItem()) : compatDatagenOutput;
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCooking(Supplier<? extends ItemLike> item) {
            return unlockedBy(item).viaCookingIngredient(() -> Ingredient.of(item.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingTag(Supplier<TagKey<Item>> tag) {
            return unlockedByTag(tag).viaCookingIngredient(() -> Ingredient.of(tag.get()));
        }

        GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder viaCookingIngredient(Supplier<Ingredient> ingredient) {
            return new GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder(ingredient);
        }

        GeneratedStonecuttingRecipeBuilder viaStonecutting(Supplier<? extends ItemLike> item) {
            return unlockedBy(item).viaStonecuttingIngrdient(() -> Ingredient.of(item.get()));
        }

        GeneratedStonecuttingRecipeBuilder viaStonecuttingTag(Supplier<TagKey<Item>> tag) {
            return unlockedByTag(tag).viaStonecuttingIngrdient(() -> Ingredient.of(tag.get()));
        }

        GeneratedStonecuttingRecipeBuilder viaStonecuttingIngrdient(Supplier<Ingredient> ingredient) {
            return new GeneratedStonecuttingRecipeBuilder(ingredient);
        }

        class GeneratedStonecuttingRecipeBuilder {

            private final Supplier<Ingredient> ingredient;

            GeneratedStonecuttingRecipeBuilder(Supplier<Ingredient> ingredient) {
                this.ingredient = ingredient;
            }

            private GeneratedRecipe create(UnaryOperator<SingleItemRecipeBuilder> builder) {
                return register(consumer -> {
                    SingleItemRecipeBuilder b = builder.apply(
                        SingleItemRecipeBuilder.stonecutting(ingredient.get(), RecipeCategory.MISC, result.get(),
                            amount));
                    if (unlockedBy != null) {
                        b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                    }
                    b.save(consumer, createLocation("stonecutting"));
                });
            }

            private GeneratedRecipe create() {
                return create(b -> b);
            }
        }

        class GeneratedCookingRecipeBuilder {

            private final Supplier<Ingredient> ingredient;
            private final SimpleCookingSerializer<?>
                FURNACE = (SimpleCookingSerializer<?>) RecipeSerializer.SMELTING_RECIPE,
                SMOKER = (SimpleCookingSerializer<?>) RecipeSerializer.SMOKING_RECIPE,
                BLAST = (SimpleCookingSerializer<?>) RecipeSerializer.BLASTING_RECIPE,
                CAMPFIRE = (SimpleCookingSerializer<?>) RecipeSerializer.CAMPFIRE_COOKING_RECIPE;
            private float exp;
            private int cookingTime;

            GeneratedCookingRecipeBuilder(Supplier<Ingredient> ingredient) {
                this.ingredient = ingredient;
                cookingTime = 200;
                exp = 0;
            }

            GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder forDuration(int duration) {
                cookingTime = duration;
                return this;
            }

            GeneratedRecipeBuilder.GeneratedCookingRecipeBuilder rewardXP(float xp) {
                exp = xp;
                return this;
            }

            GeneratedRecipe inFurnace() {
                return inFurnace(b -> b);
            }

            GeneratedRecipe inFurnace(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                return create(FURNACE, builder, 1);
            }

            GeneratedRecipe inSmoker() {
                return inSmoker(b -> b);
            }

            GeneratedRecipe inSmoker(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                create(FURNACE, builder, 1);
                create(CAMPFIRE, builder, 3);
                return create(SMOKER, builder, .5f);
            }

            GeneratedRecipe inBlastFurnace() {
                return inBlastFurnace(b -> b);
            }

            GeneratedRecipe inBlastFurnace(UnaryOperator<SimpleCookingRecipeBuilder> builder) {
                create(FURNACE, builder, 1);
                return create(BLAST, builder, .5f);
            }

            private GeneratedRecipe create(SimpleCookingSerializer<?> serializer,
                UnaryOperator<SimpleCookingRecipeBuilder> builder, float cookingTimeModifier) {
                return register(consumer -> {
                    boolean isOtherMod = compatDatagenOutput != null;

                    SimpleCookingRecipeBuilder b = builder.apply(
                        SimpleCookingRecipeBuilder.campfireCooking(ingredient.get(), RecipeCategory.MISC,
                            isOtherMod ? Items.DIRT : result.get(),
                            exp, (int) (cookingTime * cookingTimeModifier)));
                    if (unlockedBy != null) {
                        b.unlockedBy("has_item", inventoryTrigger(unlockedBy.get()));
                    }
                    b.save(consumer, createSimpleLocation(RegisteredObjects.getKeyOrThrow(serializer)
                        .getPath()));
                });
            }
        }
    }
}
