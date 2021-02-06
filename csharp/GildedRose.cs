using System.Collections.Generic;

namespace csharp
{
    public class GildedRose
    {
        IList<DefaultItem> Items;
        public GildedRose(IList<DefaultItem> Items)
        {
            this.Items = Items;
        }

        public void UpdateQuality()
        {
            for (var i = 0; i < Items.Count; i++)
            {
                Items[i].UpdateItemQuality();
            }
        }
    }
}
