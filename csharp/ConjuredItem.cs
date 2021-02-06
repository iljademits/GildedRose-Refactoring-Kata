using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp
{
    class ConjuredItem : DefaultItem
    {
        public override void UpdateItemQuality()
        {
            if (this.Quality > 1)
            {
                this.Quality -= 2;
            }
            else
            {
                this.Quality = 0;
            }
            if (this.SellIn <= 0)
            {
                if (this.Quality > 1)
                {
                    this.Quality -= 2;
                }
                else
                {
                    this.Quality = 0;
                }

            }
            this.SellIn -= 1;
        }
    }
}
