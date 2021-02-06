using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp
{
    class BackstagePass : DefaultItem
    {
        public override void UpdateItemQuality()
        {
            if (this.SellIn <= 0)
            {
                this.Quality = 0;
            }
            else
            {
                if (this.Quality > 0)
                {
                    this.Quality += 1;
                }
                if (this.SellIn < 11 && this.Quality > 0)
                {
                    this.Quality += 1;
                }
                if (this.SellIn < 6 && this.Quality > 0)
                {
                    this.Quality += 1;
                }
            }
            this.SellIn -= 1;
        }
    }
}
