using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace csharp
{
    class AgedBrie : DefaultItem
    {
        public override void UpdateItemQuality()
        {
            if (this.Quality < 50)
            {
                this.Quality += 1;
            }
            if (this.SellIn <= 0 && this.Quality < 50)
            {
                this.Quality += 1;
            }
            this.SellIn -= 1;
        }
    }
}
